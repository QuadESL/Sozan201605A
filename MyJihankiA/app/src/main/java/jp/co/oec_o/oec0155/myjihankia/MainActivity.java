package jp.co.oec_o.oec0155.myjihankia;

import android.content.ClipData;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity implements View.OnDragListener {

    @BindView(R.id.syasin1)
    ImageView syasin1;
    @BindView(R.id.kakaku1)
    TextView kakaku1;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.syasin2)
    ImageView syasin2;
    @BindView(R.id.kakaku2)
    TextView kakaku2;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.syasin3)
    ImageView syasin3;
    @BindView(R.id.kakaku3)
    TextView kakaku3;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.toridasiguti)
    ImageView toridasiguti;
    @BindView(R.id.goukeihyoji)
    TextView goukeihyoji;
    @BindView(R.id.oturihyoji)
    TextView oturihyoji;
    @BindView(R.id.tonyuguti)
    ImageView tonyuguti;
    @BindView(R.id.okane500)
    ImageView okane500;
    @BindView(R.id.okane100)
    ImageView okane100;
    @BindView(R.id.okane50)
    ImageView okane50;
    @BindView(R.id.okane10)
    ImageView okane10;
    //投入金額を入れる変数
    int tonyukingaku;
    //合計金額を入れる変数
    int goukeikingaku;
    //おつりを入れる変数
    int oturikingaku;
    //商品１の価格を入れる変数
    int kakaku1kingaku;
    //商品２の価格を入れる変数
    int kakaku2kingaku;
    //商品３の価格を入れる変数
    int kakaku3kingaku;
    //お金を入れた時の音
    MediaPlayer sound1;
    //商品が出る時の音
    MediaPlayer sound2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tonyuguti.setOnDragListener(this);
        syokiSyori();


    }

    private void syokiSyori() {
        //ボタンを全てオフにして押せなくする
        buttonAllOff();
        //商品１,２,３の価格を変数に入れる
        kakaku1kingaku = Integer.parseInt(kakaku1.getText().toString());
        kakaku2kingaku = Integer.parseInt(kakaku2.getText().toString());
        kakaku3kingaku = Integer.parseInt(kakaku3.getText().toString());
        //音を出す準備
        sound1 = MediaPlayer.create(this, R.raw.hyun1);
        sound2 = MediaPlayer.create(this, R.raw.touch1);


    }

    //ボタンをすべてオフにする
    private void buttonAllOff() {
        button.setEnabled(false);
        button.setBackgroundColor(Color.GRAY);
        button2.setEnabled(false);
        button2.setBackgroundColor(Color.GRAY);
        button3.setEnabled(false);
        button3.setBackgroundColor(Color.GRAY);

    }

    // 500円硬貨をタッチした時の処理。　ドラッグが開始される
    @OnTouch(R.id.okane500)
    public boolean touchOkane500(ImageView img) {
        ClipData clipData = ClipData.newPlainText("okane", "500");
        img.startDrag(clipData, new View.DragShadowBuilder(img), (Object) img, 0);
        return true;
    }

    // 100円硬貨をタッチした時の処理。　ドラッグが開始される
    @OnTouch(R.id.okane100)
    public boolean touchOkane100(ImageView img) {
        ClipData clipData = ClipData.newPlainText("okane", "100");
        img.startDrag(clipData, new View.DragShadowBuilder(img), (Object) img, 0);
        return true;
    }

    // 50円硬貨をタッチした時の処理。　ドラッグが開始される
    @OnTouch(R.id.okane50)
    public boolean touchOkane50(ImageView img) {
        ClipData clipData = ClipData.newPlainText("okane", "50");
        img.startDrag(clipData, new View.DragShadowBuilder(img), (Object) img, 0);
        return true;
    }

    // 10円硬貨をタッチした時の処理。　ドラッグが開始される
    @OnTouch(R.id.okane10)
    public boolean touchOkane10(ImageView img) {
        ClipData clipData = ClipData.newPlainText("okane", "10");
        img.startDrag(clipData, new View.DragShadowBuilder(img), (Object) img, 0);
        return true;
    }


    @Override
    public boolean onDrag(View v, DragEvent event) {
        // ドロップした時
        if (event.getAction() == DragEvent.ACTION_DROP) {
            // ドロップした先が投入口だったら
            if (v == tonyuguti) {
                // クリップデータを取り出して、投入金額変数に数字に変換してセットする
                ClipData clipData = event.getClipData();
                ClipData.Item item = clipData.getItemAt(0);
                tonyukingaku = Integer.parseInt((String) item.getText());
                tonyuSyori();
            }
        }
        return true;
    }

    private void tonyuSyori() {
        goukeikingaku = goukeikingaku + tonyukingaku;
        goukeihyoji.setText(String.valueOf(goukeikingaku));
        if (goukeikingaku >= kakaku1kingaku) {
            buttonOn();
        }
        if (goukeikingaku >= kakaku2kingaku) {
            button2On();
        }
        if (goukeikingaku >= kakaku3kingaku) {
            button3On();
        }
    }

    private void buttonOn() {
        button.setEnabled(true);
        button.setBackgroundColor(Color.MAGENTA);
    }

    private void button2On() {
        button2.setEnabled(true);
        button2.setBackgroundColor(Color.MAGENTA);

    }

    private void button3On() {
        button3.setEnabled(true);
        button3.setBackgroundColor(Color.MAGENTA);
    }


    @OnClick(R.id.button)
    public void onClick1() {
    oturikingaku = goukeikingaku - kakaku1kingaku;
    toridasiguti.setImageDrawable(syasin1.getDrawable());
   oturihyoji.setText(String.valueOf(oturikingaku));
        buttonAllOff();
    }

    @OnClick(R.id.button2)
    public void onClick2() {
    oturikingaku = goukeikingaku - kakaku2kingaku;
    toridasiguti.setImageDrawable(syasin2.getDrawable());
        oturihyoji.setText(String.valueOf(oturikingaku));
        buttonAllOff();
    }

    @OnClick(R.id.button3)
    public void onClick3() {
    oturikingaku = goukeikingaku - kakaku3kingaku;
    toridasiguti.setImageDrawable(syasin3.getDrawable());
        oturihyoji.setText(String.valueOf(oturikingaku));
        buttonAllOff();
    }








}
