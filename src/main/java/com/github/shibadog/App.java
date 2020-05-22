package com.github.shibadog;

import javax.inject.Singleton;

import dagger.Component;

public class App {
    // Spring bootでいうところの、SpringApplication#run()を自分で作る感じ。
    // その際に、@Componentで@Beanしている@Configurationなクラスを指定する。
    @Singleton
    @Component(modules = { TryModule.class })
    public interface Bootstrap {
        TryInterface start();
    }

    public static void main(String[] args) {
        // mavenビルド時に、勝手に@Componentがついているインタフェースを漁って勝手に作るクラス
        // Dagger{親クラス名}_{インタフェース名}#create() がネーミングルールらしい。
        // 多重も可能っぽい...Dagger{祖父クラス}_{親クラス}_{インタフェース名}#create()みたいな
        Bootstrap bootstrap = DaggerApp_Bootstrap.create();
        // ↑で作ったBootstrapになる自動作成クラスのメソッドで定義された（インジェクションされた）クラスを呼び出せる。
        // 本当のBootstarapやら、アプリの本質になるHandlerをここで呼べばよい？
        // mainの中をちゃんとしたスタートアップ処理にするのであれば、必要なクラス軍をBootstrapにはめ込んで呼び分ける感じでもよいかも？
        // なんとなくコマンドアプリに向いてそう
        TryInterface application = bootstrap.start();
        // TryInterfaceがTryModuleで指定した実態クラスであるTryInterfaceImplとして生成されて渡されているので、
        // ちゃんと実行すると実態が呼び出される。
        System.out.println(application.getPrintText());
        // ヤバイチョットタノシイ
        // そしてむっちゃかるい
    }
}
