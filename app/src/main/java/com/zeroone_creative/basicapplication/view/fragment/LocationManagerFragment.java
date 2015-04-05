package com.zeroone_creative.basicapplication.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class LocationManagerFragment extends Fragment implements LocationListener {
    private OnUpdateLocationListener mListener;
    private int mPeriodTime;
    private String mProvider;
    private Location mCurrentBestLocation;
    private LocationManager mLocationManager;

    public static LocationManagerFragment newInstance(final int request_period) {
        LocationManagerFragment fragment = new LocationManagerFragment();
        Bundle args = new Bundle();
        args.putInt("request_period", request_period);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mPeriodTime = args.getInt("request_period");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // LocationManagerを取得
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        // Criteriaオブジェクトを生成
        Criteria criteria = new Criteria();
        // Accuracyを指定(低精度)
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        // PowerRequirementを指定(低消費電力)
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        // ロケーションプロバイダの取得
        mProvider = mLocationManager.getBestProvider(criteria, true);
        registProvider();
    }

    @Override
    public void onStop() {
        super.onStop();
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnUpdateLocationListener) {
            mListener = (OnUpdateLocationListener) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (mListener != null) {
            mCurrentBestLocation = isBetterLocation(location, mCurrentBestLocation) ? location : mCurrentBestLocation;
            mListener.onUpdateLocation(mCurrentBestLocation);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        if (mProvider == null) {
            mProvider = provider;
            registProvider();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (mProvider.equals(provider)) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            mProvider = mLocationManager.getBestProvider(criteria, true);
            registProvider();
        }
    }

    private void registProvider() {
        if (mProvider != null) {
            // LocationListenerを登録
            mLocationManager.requestLocationUpdates(mProvider, mPeriodTime, 10, this);
            mCurrentBestLocation = mLocationManager.getLastKnownLocation(mProvider);
            if (mCurrentBestLocation != null && mListener != null) {
                mListener.onUpdateLocation(mCurrentBestLocation);
            }
        }
    }

    private static final int TWO_MINUTES = 1000 * 60 * 2;
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            return true;
        }
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;
        if (isSignificantlyNewer) {
            return true;
        } else if (isSignificantlyOlder) {
            return false;
        }
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }


    public interface OnUpdateLocationListener {
        public void onUpdateLocation(Location location);
    }

}