BasicApplication
================

##プロジェクトについて##
このプロジェクトは、アプリケーションを作る際の初歩のプロジェクトとして製作されたもので、一般的な通信処理のサポートや、デザインパターンをサポートするものです。
AndroidAnnotationを使用したコーディングをベースとしており、下の項目にある規約に基づいて書かれています。

##対象バージョン##
- minSDKバージョン : 15
- compileSDKバージョン : 21

##開発環境##
- AndroidStudio : 1.0以降
- JDK7.0
- 必要AndroidSDK : 21・19・17

##コーディング規約について##

>このプロジェクトのコードは以下の規約に従って書かれています。
http://qiita.com/Reyurnible/items/2de397b80391189af8e4

###使用ライブラリ：###
- Android Annotation
- Volley
- Picasso
- Gson
- その他supportパッケージなど

##App(Application)##

###1.命名規則###

####アピリケーション名####

- 普通の英語名（スペースを含んでも構わない）
- UpperCamelCase

####プロジェクト名####

- UpperCamelCase


##Src(Sorce)##


###1.命名規則###

####パッケージ名####

- すべて小文字
- スペースは削除して、アンダースコア(_)に変更する。
- srcの中のパッケージ名：
	- メインパッケージ：<リバースドメイン名>.<アプリケーション名>
	- メインパッケージ以外：<メインパッケージ>.[controller|model|view...]
- 詳細な構成については、2のフォルダパッケージ構成に記す。

####ファイル・クラス名####

- UpperCamelCase
- 名詞
- Activity、Fragment、AdapterなどはComponent名を末尾に付ける

####メソッド名####

- lowerCamelCase
- 動詞で始める。ただし、Booleanを返すメソッドはisで始める。
- プロパティへのアクセスメソッドについて
	- Booleanを返すもの	: is<UpperCamelCaseのプロパティ名>
	- それ以外を返すもの	: get<UpperCamelCaseのプロパティ名>
	- Setter			: set<UpperCamelCaseのプロパティ名>

####プロパティ名・変数名####

- lowerCamelCase
- アンダースコア(_)やドル記号(＄)で初めてはいけない
- publicではなく、staticでもないプロパティ名は、mで始める
- staticなプロパティ名は、sで始める
- メソッド中のprivateな変数には、mを付けない

####定数名(public static final)####

- 大文字とアンダースコア(_)のみを用いる。
	ex:public static final double FIELD_WIDTH = 500.0;
- 用途に応じて、以下のように接頭辞を変える。String型の識別子の値は、lowerCamelCaseに変換したものを使用する。

| 用途  | 接頭辞 |
|:---------------|-------:|
| Intentのaction | ACTION_ |
| Permission | PERMISSION_ |
| BundleやIntentのフィールド名 | EXTRA_(型名)_ |
なお、これらの定数の値は、パッケージ名で始める。
public static final String ACTION_GPS_STATUS_CHANGED = "com.example.sample.ACTION_GPS_STATUS_CHANGED";
public static final String EXTRA_GPS_LOCATION = "com.example.sample.EXTRA_GPS_LOCATION";

####SQLite####
- テーブル名とカラム名：小文字とアンダースコア(_)
- テーブル名：<モデルオブジェクト・クラス名>s
- IDカラム名：BasicColumns._ID
データベース名：<アプリケーション名>.db

###2.フォルダパッケージ構成###

- **controller**（直接的にViewを持たない処理）

	- *provider*（通信処理を管理）
	- *util*（その他の処理）

- **model**（POJOやenumを管理）

	- *pojo*（画面名かDBの構成によって更に子フォルダを作成する）
	- *enumerate*（enumを管理）
	- *system*（アプリ自体の情報を保持するクラスを管理する ex:AppConfig.java,Constants.java）

- **view**（layoutファイルと関連付けられるもの・viewcontrollerを管理）

	- *activity*（Activityを管理）
	- *adapter*（Adapterを管理）
	- *fragment*（Fragmentを管理）
	- *widget*（カスタムViewを管理）

###3.Javaコーディング規則###
コーディング規則はGoogleのJavaのコーディング規則に準拠するものとする。
[Google Java Style](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html)

[主な内容と構成](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s1-introduction)：

- [ソースファイル基礎](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s2-source-file-basics)
	- [ファイル名](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s2.1-file-name)
	- [ファイルエンコーディング](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s2.2-file-encoding)
	- [空白文字](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s2.3.1-whitespace-characters)
	- [特殊文字](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s2.3.2-special-escape-sequences)
- [ソースファイル構成](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s3-source-file-structure)
	- [ライセンス情報](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s3.1-copyright-statement)
	- [パッケージ](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s3.2-package-statement)
	- [インポート文](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s3.3-import-statements)
	- [クラスメソッドの順序付け](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s3.4-class-declaration)
- [フォーマット](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4-formatting)
	- [大括弧](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.1-braces)
	- [インデント](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.2-block-indentation)
	- [行の折り返し](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.3-one-statement-per-line)
	- [空白](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.6-whitespace)
	- [丸括弧](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.7-grouping-parentheses)
	- [enum](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.8.1-enum-classes)
	- [配列](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.8.3-arrays)
	- [switch文](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.8.4-switch)
	- [アノテーション](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.8.5-annotations)
	- [コメント](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.8.6-comments)
	- [モディファイヤ](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.8.7-modifiers)
- [ネーミング](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5-naming)
	- [パッケージ](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5.2.1-package-names)
	- [クラス](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5.2.2-class-names)
	- [メソッド](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5.2.3-method-names)
	- [コンスタント](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5.2.4-constant-names)
	- [フィールド](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5.2.5-non-constant-field-names)
	- [ローカル変数](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5.2.7-local-variable-names)
	- [型変数などの識別子](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5.2.8-type-variable-names)
	- [キャメルケースの定義](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s5.3-camel-case)
- [プログラミングプラクティス](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s6-programming-practices)
	- [@Override](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s6.1-override-annotation)
	- [例外](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s6.2-caught-exceptions)
	- [スタティックメンバ](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s6.3-static-members)
	- [ファイナライザ](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s6.4-finalizers)
- [Javadoc](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s7-javadoc)
	- [Javadocの書式設定方法](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s7.1-javadoc-formatting)
	- [仕様場面](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s7.3-javadoc-where-required)

###4.その他Javaコーディング規約と変更###

####型変換について####
型の変換については、基本的にvalueOfメソッドを用いて行うようにし、それで対応できない場合には別のメソッドを利用するようにする。

```
変換する対象の型名.valueOf(T)
```

####例外処理について####
#####[例外を無視してはいけない](http://www.textdrop.net/android/code-style-ja.html#exceptionsIgnore)#####

悪い例：

```
void setServerPort(String value) {
    try {
        serverPort = Integer.parseInt(value);
    } catch (NumberFormatException e) { }
}
```

このように例外を無視していると、いつか誰かがつまずき、コードにある地雷を踏んでしまいます。コードにあるすべての Exception は処理しなければなりません。どのように処理するかは、場合により異なります。

例外の処理の仕方：

- *メソッドの呼び出し元に例外を投げます。*

```
void setServerPort(String value) throws NumberFormatException {
    serverPort = Integer.parseInt(value);
}
```

- *適度に抽象化した新しい例外を投げます。*

```
void setServerPort(String value) throws ConfigurationException {
    try {
        serverPort = Integer.parseInt(value);
    } catch (NumberFormatException e) {
        throw new ConfigurationException("Port " + value + " is not valid.");
    }
}
```

- *catch {} ブロックのなかで行儀よくエラーに対処して、適切な値を代入しておきます。*

```
/** Set port. If value is not a valid number, 80 is substituted. */
void setServerPort(String value) {
    try {
        serverPort = Integer.parseInt(value);
    } catch (NumberFormatException e) {
        serverPort = 80;  // default port for server
    }
}
```

- *Exception をキャッチして、新しい RuntimeException を投げます。しかし、これは危険です。この手段をとるのは、そのエラーが発生したときにはクラッシュさせるのがふさわしいといった積極的な理由がある場合のみにしてください。*

```
/** Set port. If value is not a valid number, die. */
void setServerPort(String value) {
    try {
        serverPort = Integer.parseInt(value);
    } catch (NumberFormatException e) {
        throw new RuntimeException("port " + value " is invalid, ", e);
    }
}
```

元の例外が RuntimeException のコンストラクタの引数に渡されていることに注意してください。もしJava 1.3でもコンパイルする必要があるなら、元の例外を省略する必要があります。

- *最後の手段: 自信をもって例外を無視するのが本当に適切だと言えるのであれば、例外を無視しても構いません。ただし、なぜそうするのか、きちんとした理由をコメントに入れておく必要があります。*

```
/** If value is not a valid number, original port number is used. */
void setServerPort(String value) {
    try {
        serverPort = Integer.parseInt(value);
    } catch (NumberFormatException e) {
        // Method is documented to just ignore invalid user input.
        // serverPort will just be unchanged.
    }
}
```

#####[汎用的例外の使用について](http://www.textdrop.net/android/code-style-ja.html#exceptionsAll)#####

悪い例：

```
try {
    someComplicatedIOFunction();        // may throw IOException
    someComplicatedParsingFunction();   // may throw ParsingException
    someComplicatedSecurityFunction();  // may throw SecurityException
    // phew, made it all the way
} catch (Exception e) {               // I'll just catch all exceptions
    handleError();                      // with one generic handler!
}
```

ほとんどの場合、汎用の Exception や Throwable をキャッチするのは適切ではありません。また、できるだけ Throwable を使わない方が望ましいです。

***汎用の Exception をキャッチする代わりに、次のような手段をとることができます。***

- try の後、例外ごとに別の catch ブロックでキャッチする。これは不恰好に見えるかもしれませんが、すべての Exception をキャッチするよりましです。catch ブロックでは、同じコードを繰り返しすぎないよう注意してください。
- 複数の try ブロックを使って、きめ細かくエラー処理するようリファクタリングする。パースから IO を分離して、それぞれ個別にエラー処理するようにしてください。
- 例外を投げ直す。どうしてもこのレベルで例外をキャッチする必要がない多くの場合には、単にそのメソッドに例外を投げさせてください。


####[ファイナライザについて](http://www.textdrop.net/android/code-style-ja.html#finalizers)####

ファイナライザとは、ここでは、オブジェクトがガベージコレクトされるときに実行されるコードのことを指す。

ファイナライザを使はないようにする。うまく例外処理することで、ファイナライザでやりたいことは大抵可能なため、どうしてもファイナライザが必要であれば、close() メソッド（のようなもの）を定義して、そのメソッドをいつ呼び出す必要があるのか、ドキュメントに明記する。

####[インポートについて](http://www.textdrop.net/android/code-style-ja.html#imports)####

コードをインポートする際には、必ず ワイルドカードを使用しないスタイルを使用する。
ただし、Java標準ライブラリ（java.util.*, java.io.* など）とユニットテストコード（junit.framework.*）についてはその限りではない。

また、順序については以下のとおりに行う。

1. Android 関係のインポート
2. サードパーティ製ライブラリ (com, junit, net, org)のインポート
3. java および javax 関連のインポート

各グループ内では、アルファベット順にインポートします。
大文字は小文字よりも前にあるものと見なします。（例えば Z は a よりも前にある）。
大きなグループ分け (android, com, junit, net, org, java, javax) の間には空行を入れておくべきです。

####[コメント/Javadocについて](http://www.textdrop.net/android/code-style-ja.html#javadoc)####

Javadocに入れるべき項目と順番

1. コピーライト文
2. package 文
3. import 文
4. クラスやインタフェースの宣言
5. クラスやインタフェースの説明

そして、各ブロックの間は1行空けておく。

```Example.java
/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
```

また、クラスと複雑なパブリックメソッドにはすべて、少なくともそのクラスやメソッドが何をするものか説明する一文を Javadoc コメントとして入れなければなりません。

####[メソッドについて](http://www.textdrop.net/android/code-style-ja.html#shortmethods)####

メソッドはできるだけ小さく、ポイントを絞ったものにする。
目安として、40行を超えるようであれば、プログラムの構造に影響を与えることなく分割できないか検討する。

####[インデントについて](http://www.textdrop.net/android/code-style-ja.html#indentation)####

ブロックのインデントにはスペース4つを使用する。タブは使用しない。
関数呼び出しと引数を含んだ行の折り返しは、スペース8つでインデントを行う

####[Java 1.5 アノテーションについて]####

アノテーションは同じ言語要素にある他の修飾子よりも前に置く。
アノテーションが複数ある場合、パラメータ付きのアノテーションの場合には、アルファベット順に1行ずつ並べる。
Java 1.5 で定義された3つのアノテーションについて、Android 標準のプラクティスは以下のようになります。

- @Deprecated

その要素の利用がもはや推奨されない時には、@Deprecated アノテーションを必ず付けなければならない。@Deprecated アノテーションを使うときには、@deprecated Javadoc タグも付けなければなりません。
そして、その代わりに使う実装について説明しておく。@Deprecated メソッドはまだ動作がサポートされていることも意味していることを忘れない。
もし @deprecated Javadoc タグが付いた古いコードがあれば、@Deprecated アノテーションを追加しておく。

- @Override

メソッドがスーパークラスにある宣言や実装をオーバーライドしているときには、@Override アノテーションを必ず付けなければならない。

- @SuppressWarnings

@SuppressWarnings アノテーションは、どうしても警告を取り除けない事情がある場合にのみ使用する。警告を「取り除けない」と判断したときには、@SuppressWarnings アノテーションを付けなければならない。すべての警告がコードにある実際の問題を反映するようにしておくためです。
@SuppressWarnings アノテーションが必要になったときには、その直前に TODO コメントを入れて、その警告を「取り除けない」事情について説明しておかなくてはいけません。こうすることで、やっかいなインタフェースをもつ問題のあるクラスだということがわかります。

###5.TODOについて###

コード中の TODO コメントは、一時的なものや、短期的な解決策、その場しのぎで完全ではないところなどに使用する。
TODO にはすべて大文字の TODO にコロンを続けたものにする。
また、Gitのissueなどに絡んだTODOである場合は、issue の ID をつけ、解決を他者に依頼する場合は @ マークの後に相手のユーザー名を記述するようにし、文末に中括弧で囲み記述する（[...]）。

例：

```

// TODO: Remove this code after the UrlTable2 has been checked in.

// TODO: Fix this error[Issue:30 @shun]

```

###6.[AndroidAnnnotation](http://androidannotations.org/)による書き方###
Activityの書き出しは、クラス名の前に@EActivity(R.layout.レイアウトファイル名)を付けて始める。
Activity全体にかかるパラメーター

- タイトルバーなしの場合	->	@NoTitleを付ける。
- フルスクリーンの場合	->	@Fullscreen

```SampleActivity.java

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_sample)
public class SampleActivity extends Activity {

	//Viewのインスタンス生成
	@ViewById(R.id.sample_textview)
	TextView mSampleTextView;

	//Applicationのインスタンス生成
	@App
	SampleApplication mSampleApplication;

	//Animationのインスタンス生成
	@AnimationRes
	Animation mSampleAnimation;

	//Serviceのインスタンス生成
	@SystemService
	SampleManager mSampleManager;

	//View生成後に呼び出されるメソッド(onWindowFocusChangedと同様のタイミング)
	//@onAfterViews,@OnAfterInjectのメソッドは一つしか定め、メソッドの先頭にonをつけたアノテーション名として定義する。
	@AfterViews
  	void onAfterViews() {
		mSampleTextView.setText("Hello World");
	}

	//OnClick処理ViewにsetOnClick()を付けなくても使用できる。
	@Click({R.id.sample_button_1, R.id.sample_button_2})
	void updateBookmarksClicked() {
		sampleAsync();
	}

	//Asyncタスクのメソッド
	@Background
	void sampleAsync(){
		sampleUiThread();
	}

	//メインスレッドで処理を行うメソッド。Backgroundメソッドから呼び出してもMainスレッドで処理を行う用にハンドラー処理を行う。
	@UiThread
	void sampleUiThread(){
	}

}

```

###7.Activity・Fragmentのインスタンス生成について###
Activity、Fragmentのインスタンスの生成は,AndroidAnnotationにある＠Extraによって行う。
また、渡すパラメーターの名は以下のように、1のプロパティの命名規則に従う。また、@Extra内の値には、プロパティ名から先頭のmを取りスネークケースに変換したものを使用する。
また、オブジェクトの受け渡しを行う際にはGsonによってString文字列に変換して渡し、受け取り側は@AfterInject（値を受け取ったタイミング）のメソッド中でObjectに戻すようにする。

```ToSampleActivity.java　
/**
* FromSampleActivity.javaから遷移した先の画面
*/
@EActivity(R.layout.activity_sample)
public class ToSampleActivity extends Activity {

	@Extra("str_param")
    String mStrParam;

	@Extra("sample_json")
	String mSampleJson;

	private Sample mSample;

	@AfterIngect
	onAfterInject() {
		mSample = new Gson().fromJson(mSampleJson, Sample.class);
	}

}

```

```FromSampleActivity.java　
/**
* ToSampleActivity.javaへ遷移するだけの画面
*/
@EActivity(R.layout.activity_sample)
public class FromSampleActivity extends Activity {

	private Sample mSample;

	@AfterViews
	onAfterViews() {
		//intent生成の際、本来であればGCによってメモリを押さえられるgetApplicationContextを使用するが、Annotaionを使用したActivity内で行う場合エラーとなるため、thisによってContextの生成を行うように注意する。
		ToSampleActivity_.intent(this).mStrParam("hoge").mSampleJson(new Gson().toJson(mSample, Sample.class).start();
	}

}

```

###8.ログの出力について###

ログ記録は必要不可欠ですが、適度に簡潔にしておかないとパフォーマンスに悪影響を与え、役に立たなくなります。
ログ記録ファシリティにはレベルが5つあり、各レベルをいつどのように使うべきかは以下の通りです。

- ERROR: このレベルは何か致命的なことが発生したときに使うべきです。
つまり、ユーザにとって目に見えて重大で、データの削除やアプリケーションのアンインストール、データパーティションの削除、電話全体の再起動（あるいは、もっともっと悪いこと）といったことを明確にしないと復旧不能な問題が発生したときです。このレベルは必ずログに記録しておいてください。 通常、ERROR レベルでログ記録するような問題は、統計収集サーバに報告するのが望ましいです。
- WARNING: このレベルは深刻な予期せぬことが発生したときに使うべきです。
つまり、ユーザにとって目に見えて重大ですが、特定のアクションを実行すればデータを失うことなく復旧可能な問題が発生したときです。このアクションには、しばらく待つだけやアプリを再起動することから、アプリケーションの新しいバージョンを再ダウンロードしたり、デバイスをリブートすることまで、いろいろあります。このレベルは必ずログに記録しておいてください。 WARNING レベルでログ記録するような問題も、統計収集サーバへの報告を検討するのが望ましいです。
- INFORMATIVE: このレベルは多くの人にとって関心のあることが発生したとき、それを通知するために使うべきです。
つまり、広範囲な影響があるかもしれませんが、必ずしもエラーではないような状況です。これは、そのドメインにおいて最も信頼できると思われるモジュールによってのみログ記録されるべきです。（信頼できないコンポーネントによって、ログが二重に記録されるのを防ぐため）。このレベルは必ずログに記録しておいてください。
- DEBUG: このレベルはデバイスに何が発生しているのか、想定外の動作を調査、デバッグするための情報を記録するときに使うべきです。
あなたのコンポーネントに何が起こっているのか、必要十分な情報だけをログに記録すべきです。もしデバッグログがログの大部分を占めるようならば、それらは VERBOSE レベルで記録すべきです。DEBUG レベルはリリースビルドでも記録されるため、if (LOCAL_LOG) ブロックや if (LOCAL_LOGD) ブロックで囲んでおく必要があります。この LOCAL_LOG[D] はクラスやサブコンポーネントで定義され、こうしたログをすべて無効にできるようになっています。 したがって、if (LOCAL_LOG) ブロックにはアクティブな論理を入れてはいけません。ログに必要な文字列構築も if (LOCAL_LOG) ブロックのなかに入れておく必要があります。もし if (LOCAL_LOG) ブロックの外側で文字列構築しようとしているなら、ログ記録の呼び出しをメソッド呼び出しにリファクタリングしてはいけません。依然として if (localLOGV) を使っているコードもあります。これもまだ使えるのですが、名前付けは Android の標準に従っていません。
- VERBOSE: 以上のレベルのいずれにも当てはまらないログはすべて、VERBOSE レベルを使うべきです。
このレベルはデバッグビルドでのみログが記録されるよう、if (LOCAL_LOGV) ブロック（あるいは同等のもの）で囲んでおくべきです。こうすることで、デフォルトではコンパイルされなくなります。ログに必要な文字列構築は、リリースビルドに入らないよう、if (LOCAL_LOGV) ブロックのなかに入れておく必要があります。

重要な点として、DEBUGとVERBOSEは、リリースビルドではログが出力されないようif(LOCAL_LOGD)で囲むようにする。
また、ログの出力においてタグは以下のようにクラスのシンプルネームを使用する。

```

Log.d(getClass().getSimpleName(), "Sample log");

```



##Res(Resource)##

###1.命名規則###

####リソース名とID####

- 小文字とアンダースコア(_)のみを用いる。 ※idをCamelCaseとしている例もあるが、drawableのファイル名にならいa-z0-9_.のいずれかでなくてはいけないので、それに合わせる。
- リソース名は、アルファベット順にソートされて表示されるので、関連するものが固まって表示されるように命名する。
	- activityやfragment、item、ic、img、bgなどの接頭辞を必ず付ける。
	- 例えば、グローバルなdrawableはbackground、horizontal_progress_bar、seek_bar_thumbなど、「何」
を先頭に付ける。アイコンなどは、ic_で始める。
	- 一画面でしか使用されないローカルなdrawableは、その「場所」を先頭に付ける。例：「img_フラグメント名_用途名」
	- 文字列を画像にして使用する場合は、「string_フラグメント名_文字列の一部」と命名する

###2.Layoutのコーディング###

レイアウトは基本的に、階層が深くならないように配慮して書く。

####RelativeLayoutについて####
RelativeLayout を使用して記述する場合は、基準となる View から先に記述していき、先に ID をつけるようにする。

####パディングについて####
パッディングは、対象の要素の階層よりも一つ下の要素に対して余白を設けたい場合に使用する。
また、padding は使用せずに、padding-left、padding-top、padding-right、padding-bottom を使用し個別に指定するようにする。

####マージンについて####
マージンは、対象の要素と同一の階層の要素に対して余白を設けたい場合に使用する。
また、要素1と要素2がxmlで上から並んでいる場合。要素2にマージンを指定し、余白を作るようにする。

####Width と Height について####
fill_parent は使用しないようにし、match_parent を使用する。
また、後ほどコードから値を変更する可能性がある場合は wrap_content を指定しておく。dp によって絶対値指定する場合は dimen の値を参照するようにする。

###3.Resource###

####Color####

色は、最低限以下の3を用意する。

- primary(メインの色 : 7割)
- accent(サブの色 : 2割)
- primary_text(テキストの色 : 1割)

また、更に細かい色を追加する場合は、original_{色名 a~z0~9} という命名規則で追加を行う。
また、透明度を指定をした色については、 {色名}_alpha という形で用意するようにする。

####Dimens####

用意する値は、全て奇数ではない48の因数または、倍数を使用する。
また、View と Layout でファイルを分け、View の値は view_dimens.xml、Layout の値は layout_dimens.xml にそれぞれ記述する。他に、TextView の文字の大きさやは、 text_dimens.xml に記述する。
padding、 margin など縦横の向きのある値は vertical と horizontal の2つを用意する。
View には、drawable で使用する値を記述する。

####Strings####

Layout 内に使用する。文字列については、string.xml に記述する。
AndroidManifest などの XML に記述する値については、app_config.xml を用意し、そちらに書くようにする。

java内に使用する文字列については、staticなフィールドで使用するモノ以外についてはこちらに記述する。
apikey などの設定の値に関してはこちらに記述せず、AppConfig.java などにstaticな値として記述する。

##その他・参考##

[Android Open Source Project](http://source.android.com/source/code-style.html)
[日本語訳](http://www.textdrop.net/android/code-style-ja.html)
