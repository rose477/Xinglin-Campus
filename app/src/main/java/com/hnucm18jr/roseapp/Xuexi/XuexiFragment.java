package com.hnucm18jr.roseapp.Xuexi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnucm18jr.roseapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XuexiFragment extends Fragment {


    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView2;
    List<Zuoye> mList=new ArrayList<>();
    List<Kecheng> mList2=new ArrayList<>();

    ImageView mImageView,mImageView2,mImageView3,mImageView4;
    public static final int TAKE_PHOTO = 1;//声明一个请求码，用于识别返回的结果
    private Uri imageUri;
    private final String filePath = Environment.getExternalStorageDirectory() + File.separator + "output_image.jpg";
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xuexi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView=getActivity().findViewById(R.id.recyle);
        mRecyclerView.setAdapter(new MyAdapter());
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));//可以表述一行显示item的数量，并且可以设置列表的方向
        mRecyclerView2=getActivity().findViewById(R.id.recler);

        mRecyclerView2.setAdapter(new MyAdapter2());
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));//可以表述一行显示item的数量，并且可以设置列表的方向
        mRecyclerView2.setNestedScrollingEnabled(false);

        mImageView=getActivity().findViewById(R.id.imageView8);
        mImageView2=getActivity().findViewById(R.id.imageView9);
        mImageView3=getActivity().findViewById(R.id.imageView6);
        mImageView4=getActivity().findViewById(R.id.add);
        mImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SuopingActivity.class);
                startActivity(intent);
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动态请求相机权限
                requestPermission();  //在其中若用户给予权限则请求相机拍照
            }
        });
        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getActivity(),LaoshiActivity.class);
               startActivity(intent);
            }
        });
        mImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BianqianActivity.class);
                startActivity(intent);

            }
        });
        for (int i=1;i<10;i++){

            Zuoye zuoye=new Zuoye();
            zuoye.name="《2020秋-胡老师JavaEE》";
            zuoye.title="JavaEE第"+i+"次作业";
            zuoye.time="2000.11.19 12:22截止";
            mList.add(zuoye);

        }
        for (int i=1;i<10;i++){

            Kecheng zuoye=new Kecheng();
            zuoye.name="《2020秋-胡老师JavaEE》";
            zuoye.title="18计科二班";
            zuoye.time=i+"0%";
            mList2.add(zuoye);

        }


    }
    //动态请求权限
    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        } else {
            //调用
            requestCamera();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults != null && grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case 1: {
                    requestCamera();
                }
                break;
            }
        }
    }


    private void requestCamera() {
        File outputImage = new File(filePath);
                /*
                创建一个File文件对象，用于存放摄像头拍下的图片，我们把这个图片命名为output_image.jpg
                并把它存放在应用关联缓存目录下，调用getExternalCacheDir()可以得到这个目录，为什么要
                用关联缓存目录呢？由于android6.0开始，读写sd卡列为了危险权限，使用的时候必须要有权限，
                应用关联目录则可以跳过这一步
                 */
        try//判断图片是否存在，存在则删除在创建，不存在则直接创建
        {
            if (!outputImage.getParentFile().exists()) {
                outputImage.getParentFile().mkdirs();
            }
            if (outputImage.exists()) {
                outputImage.delete();
            }

            outputImage.createNewFile();

            if (Build.VERSION.SDK_INT >= 24) {
                imageUri = FileProvider.getUriForFile(getActivity(),
                        "com.example.mydemo.fileprovider", outputImage);
            } else {
                imageUri = Uri.fromFile(outputImage);
            }
            //使用隐示的Intent，系统会找到与它对应的活动，即调用摄像头，并把它存储
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
            //调用会返回结果的开启方式，返回成功的话，则把它显示出来
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //处理返回结果的函数，下面是隐示Intent的返回结果的处理方式，具体见以前我所发的intent讲解
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == getActivity().RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        int Matrix = getBitmapDegree(filePath);
                       Intent intent = new Intent(getActivity(),ShibieActivity.class);
                       intent.putExtra("filepath",filePath);
                        bitmap=rotateBitmapByDegree(bitmap,Matrix);
                        //picture.setImageBitmap(bitmap);
                        //将图片解析成Bitmap对象，并把它显现出来
                        ByteArrayOutputStream baos=new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                        byte [] bitmapByte =baos.toByteArray();
                        intent.putExtra("bitmap", bitmapByte);
                        startActivity(intent);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    //设置保存拍照图片——>再次关闭app重新打开显示为上次拍照照片
    private void setDefualtImage() {
        File outputImage = new File(filePath);
        if (!outputImage.exists()) {
            return;
        }
        //picture.setImageBitmap(BitmapFactory.decodeFile(filePath));
    }
    /**
     * 获取图片的旋转角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照指定的角度进行旋转
     *
     * @param bitmap 需要旋转的图片
     * @param degree 指定的旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bitmap, int degree) {
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        if (bitmap != null && !bitmap.isRecycled()) {
//            bitmap.recycle();
//        }
        return newBitmap;
    }

    public class MyAdapter2 extends RecyclerView.Adapter<MyHolder2>{

        @NonNull
        @Override
        public MyHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.kecheng_item,parent,false);
            MyHolder2 myViewHolder2=new MyHolder2(view);

            return myViewHolder2;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder2 holder, int position) {
            Kecheng zuoye=mList2.get(position);
            holder.mTextView.setText(zuoye.name);
            holder.mTextView2.setText(zuoye.title);
            holder.mTextView3.setText(zuoye.time);
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),KechengActivity.class);
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return mList2.size();
        }
    }

    public class MyHolder2 extends RecyclerView.ViewHolder {
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;
        ImageView mImageView;
        CardView mCardView;
        public MyHolder2(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.title2);
            mTextView2 = itemView.findViewById(R.id.name);
            mTextView3 = itemView.findViewById(R.id.jindu);
            mImageView = itemView.findViewById(R.id.image);
            mCardView=itemView.findViewById(R.id.kecheng);

        }
    }
    public class MyAdapter extends RecyclerView.Adapter<MyHolder>{
        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view=LayoutInflater.from(getActivity()).inflate(R.layout.zuoye_item,parent,false);
            MyHolder myViewHolder=new MyHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            Zuoye zuoye=mList.get(position);
            holder.mTextView.setText(zuoye.title);
            holder.mTextView2.setText(zuoye.name);
            holder.mTextView3.setText(zuoye.time);


        }

        @Override
        public int getItemCount() {

            return mList.size();
        }
    }


    public class MyHolder   extends RecyclerView.ViewHolder {
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.title);
            mTextView2 = itemView.findViewById(R.id.name);
            mTextView3 = itemView.findViewById(R.id.time);

        }
    }




}