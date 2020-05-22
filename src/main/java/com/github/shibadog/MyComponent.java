package com.github.shibadog;

import javax.inject.Inject;

public class MyComponent {
    // field injectionはprivateだとできない。
    @Inject
    TryInterface service;

    public void run() {
        // 呼び出されたアクティビティ内で、Daggerによって自動生成される、Contextを取り出す。
        MyContext context = DaggerMyContext.create();

        // 生成されたContextクラスのinjectメソッド（これは自分で定義しているが）に対して、
        // 自身を設定することによって、@Injectをつけたフィールドに対して、
        // DaggerがModuleの定義に従ってインスタンスをセットする。
        context.inject(this);

        // セットされたインスタンスを使って処理実行。
        System.out.println(service.getPrintText());

        // これはなかなかたのしそう...
        // 2016年の記事にこの使い方の記載があったため、結構昔からあるものっポイ。
        // JavaFXと融合させたらちょっと楽しいかもしれない。
        // 素直にJavaFXのBeanの勉強をしたら同じことができるのかもしれないが。。。
    }
}