package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameFourActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView[] titles1 = new TextView[2];
    private TextView[] titles2 = new TextView[4];
    private TextView[] titles = new TextView[2];
    private String[][] title = new String[2][4];
    private int[][] examples = new int[2][4];
    private int[][] examples1 = new int[2][4];
    private int[][] examples2 = new int[2][4];
    private String[][] contents = new String[2][4];
    private ImageView iv_example,iv_example1,iv_example2;
    private TextView tv_content;
    private int first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_four);
        InitViews();
        InitDatas();
        InitEvents();
        selected_first(0);
    }

    private void InitDatas() {
        title[0][0] = "黑白照片上色";
        title[0][1] = "图像风格转换";
        title[0][2] = "人物动漫化";
        title[0][3] = "图像对比度增强";
        title[1][0] = "图像去雾";
        title[1][1] = "图像无损放大";
        title[1][2] = "图像色彩增强";
        title[1][3] = "图像清晰度增强";
        examples[0][0] = R.drawable.game4_11;
        examples[0][1] = R.drawable.game4_12;
        examples[0][2] = R.drawable.game4_13;
        examples[0][3] = R.drawable.game4_14;
        examples[1][0] = R.drawable.game4_21;
        examples[1][1] = R.drawable.game4_22;
        examples[1][2] = R.drawable.game4_23;
        examples[1][3] = R.drawable.game4_24;
        contents[0][0] = "系统将使用人工智能AI对黑白照片进行上色，智能识别黑白图像内容并填充色彩，使黑白图像变得鲜活";
        contents[0][1] = "系统将使用人工智能AI对对图像进行风格转化，将图像转换成卡通画、铅笔画、哥特油画等9种艺术风格，可用于开展趣味活动，或集成到美图应用中对图像进行风格转换";
        contents[0][2] = "系统运用对抗生成网络技术，结合人脸检测、头发分割、人像分割等技术，为用户量身定制千人千面的二次元动漫形象，并支持通过参数设置，生成戴口罩的二次元动漫人像";
        contents[0][3] = "系统将使用人工智能AI智能调整过暗或者过亮图像的对比度，使图像更加鲜明\n";
        contents[1][1] = "系统将使用人工智能AI对浓雾天气下拍摄从而导致细节无法辨认的图像进行去雾处理，还原更清晰真实的图像";
        contents[1][1] = "系统将使用人工智能AI将图像在长宽方向各放大两倍，保持图像质量无损；可用于彩印照片美化、监控图片质量重建等场景";
        contents[1][2] = "系统将使用人工智能AI智能调节图片的色彩饱和度、亮度、对比度，使得图片内容细节、色彩更加逼真";
        contents[1][3] = "系统将使用人工智能AI对压缩后的模糊图像实现智能快速去噪，优化图像纹理细节，使画面更加自然清晰";
        examples1[0][0] = R.drawable.game4_111;
        examples1[0][1] = R.drawable.game4_121;
        examples1[0][2] = R.drawable.game4_131;
        examples1[0][3] = R.drawable.game4_141;
        examples1[1][0] = R.drawable.game4_211;
        examples1[1][1] = R.drawable.game4_221;
        examples1[1][2] = R.drawable.game4_231;
        examples1[1][3] = R.drawable.game4_241;
        examples2[0][0] = R.drawable.game4_112;
        examples2[0][1] = R.drawable.game4_122;
        examples2[0][2] = R.drawable.game4_132;
        examples2[0][3] = R.drawable.game4_142;
        examples2[1][0] = R.drawable.game4_212;
        examples2[1][1] = R.drawable.game4_222;
        examples2[1][2] = R.drawable.game4_232;
        examples2[1][3] = R.drawable.game4_242;
    }

    private void InitEvents() {
        titles[0].setOnClickListener(this);
        titles[1].setOnClickListener(this);
        titles1[0].setOnClickListener(this);
        titles1[1].setOnClickListener(this);
        for (int i=0;i<4;i++){
            titles2[i].setOnClickListener(this);
        }
    }

    private void InitViews() {
        titles[0] = findViewById(R.id.game4_title1);
        titles[1] = findViewById(R.id.game4_title2);
        titles1[0] = findViewById(R.id.game4_title1_1);
        titles1[1] = findViewById(R.id.game4_title1_2);
        titles2[0] = findViewById(R.id.game4_title2_1);
        titles2[1] = findViewById(R.id.game4_title2_2);
        titles2[2] = findViewById(R.id.game4_title2_3);
        titles2[3] = findViewById(R.id.game4_title2_4);
        iv_example = findViewById(R.id.game4_example_iv);
        tv_content = findViewById(R.id.game4_content);
        iv_example1 = findViewById(R.id.game4_example1_iv);
        iv_example2 = findViewById(R.id.game4_example2_iv);
    }

    private void selected_first(int i){
        first = i;
        reset_first();
        titles1[i].setBackground(getResources().getDrawable(R.drawable.top_radius_gray_bg));
        for (int j=0;j<4;j++){
            titles2[j].setText(title[i][j]);
        }
        titles2[0].setTextColor(getResources().getColor(R.color.text_selected));
        titles[0].setText(title[i][0]);
        titles[1].setText(title[i][0]);
        iv_example.setImageResource(examples[i][0]);
        iv_example1.setImageResource(examples1[i][0]);
        iv_example2.setImageResource(examples2[i][0]);
        tv_content.setText(contents[i][0]);
    }

    private void selected_second(int i){
        reset_second();
        titles2[i].setTextColor(getResources().getColor(R.color.text_selected));
        titles[0].setText(title[first][i]);
        titles[1].setText(title[first][i]);
        iv_example.setImageResource(examples[first][i]);
        iv_example1.setImageResource(examples1[first][i]);
        iv_example2.setImageResource(examples2[first][i]);
        tv_content.setText(contents[first][i]);
    }

    private void reset_first(){
        titles1[0].setBackground(getResources().getDrawable(R.drawable.top_radius_lightgray_bg));
        titles1[1].setBackground(getResources().getDrawable(R.drawable.top_radius_lightgray_bg));
        for (int i=0;i<4;i++){
            titles2[i].setTextColor(getResources().getColor(R.color.text_unselected));
        }
    }

    private void reset_second(){
        for (int i=0;i<4;i++){
            titles2[i].setTextColor(getResources().getColor(R.color.text_unselected));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.game4_title1_1:
                selected_first(0);
                break;
            case R.id.game4_title1_2:
                selected_first(1);
                break;
            case R.id.game4_title2_1:
                selected_second(0);
                break;
            case R.id.game4_title2_2:
                selected_second(1);
                break;
            case R.id.game4_title2_3:
                selected_second(2);
                break;
            case R.id.game4_title2_4:
                selected_second(3);
                break;
        }
    }
}