package com.example.mytest.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytest.Common.CommonUtils;
import com.example.mytest.MyAdapter.CommentRecyclerViewAdapter;
import com.example.mytest.R;
import com.example.mytest.db.CommentBean;

import java.util.ArrayList;
import java.util.List;

public class PostCommentBottomDialog extends DialogFragment {
    private View view;
    private int num;
    private RecyclerView recyclerView;
    private CommentRecyclerViewAdapter adapter;
    private ImageView close;
    private TextView tv_confirm;
    private List<CommentBean> commentBeans;
    private List<String> comments;
    private List<Integer> head_images;
    private OnCommentConfirmListener listener;
    private EditText editText;

    public void setListener(OnCommentConfirmListener listener){
        this.listener = listener;
    }
    public PostCommentBottomDialog(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.comment_bottom_dialog,container,false);
        InitViews();
        InitDatas();
        InitEvents();
        return view;
    }

    private void InitEvents() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnConfirm();
                    CommentBean commentbean = new CommentBean(R.drawable.my_head_image,"我",
                            editText.getText().toString(),"09-20");
                    adapter.add_comment(commentbean);
                    editText.setText("");
                }
                Toast.makeText(getContext(),"评论成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InitDatas() {
        commentBeans = new ArrayList<>();
        comments = new ArrayList<>();
        head_images = new ArrayList<>();
        comments.add("追寻先辈印记，铭记历史瞬间！");
        comments.add("幸福生活来之不易！");
        comments.add("红船精神！");
        comments.add("真好看");
//        head_images.add(R.drawable.head_image1);
//        head_images.add(R.drawable.head_image2);
//        head_images.add(R.drawable.head_image3);
//        head_images.add(R.drawable.head_image4);
//        head_images.add(R.drawable.head_image5);
//        head_images.add(R.drawable.head_image6);
//        head_images.add(R.drawable.head_image7);
//        head_images.add(R.drawable.head_image8);
//        head_images.add(R.drawable.head_image9);
        head_images.add(R.drawable.my_head_image1);
        head_images.add(R.drawable.my_head_image2);
        List<String> names = new ArrayList<>();
        names.add("奋斗的弄潮儿");
        names.add("有志青年");
        for (int i=0;i<num;i++){
            commentBeans.add(new CommentBean(head_images.get(i%2),names.get(i%2),comments.get(i%4),"08-06"));
        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new CommentRecyclerViewAdapter(getContext(),commentBeans);
        recyclerView.setAdapter(adapter);
    }

    private void InitViews() {
        recyclerView = view.findViewById(R.id.comment_dialog_rv);
        close = view.findViewById(R.id.comment_dialog_close);
        tv_confirm = view.findViewById(R.id.comment_dialog_confirm);
        editText = view.findViewById(R.id.post_comment_et);
    }

    public interface OnCommentConfirmListener{
        public void OnConfirm();
    }

    public static PostCommentBottomDialog newInstance(int num){
        PostCommentBottomDialog dialog = new PostCommentBottomDialog();
        dialog.num = num;
        // 设置主题，这里只能通过xml方式设置主题，不能通过Java代码处理，因为这是getWindow还是null，
        // 而且window的几乎所有属性，都可以通过xml设置
        dialog.setStyle(STYLE_NORMAL, R.style.MyDialogTheme);
        // 设置触摸、点击弹窗外部不可关闭
        dialog.setCancelable(true);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        resizeDialogFragment();
        resetDialogFragment();
    }

    private void resetDialogFragment() {
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //window.setDimAmount(0.0f);
        window.setAttributes(layoutParams);
    }

    private void resizeDialogFragment() {
        Dialog dialog = getDialog();
        if(dialog != null){
            Window window = dialog.getWindow();
            WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
            lp.width = CommonUtils.getWindowWidth(getContext());
            if(window != null){
                window.setLayout(lp.width, CommonUtils.dip2px(getContext(),470));
            }
        }
    }
}
