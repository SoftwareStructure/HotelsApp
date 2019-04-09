    package ify.com.hotelsapp;

    import android.content.ContentResolver;
    import android.content.Intent;
    import android.net.Uri;
    import android.os.Handler;
    import android.support.annotation.NonNull;
    import android.support.annotation.Nullable;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.webkit.MimeTypeMap;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.storage.FirebaseStorage;
    import com.google.firebase.storage.OnProgressListener;
    import com.google.firebase.storage.StorageReference;
    import com.google.firebase.storage.UploadTask;
    import com.squareup.picasso.Picasso;

    import models.Upload;

    public class UploadImageActivity extends BaseActivity implements View.OnClickListener {

        private static final int PICK_IMAGE_REQUEST = 1;

        private Button mButtonChooseImage;
        private Button mButtonUpload;
        private TextView mTextViewShowUploads;
        private EditText mEditTextFileName;
        private ImageView mImageView;

        private Uri mImageUri;

        private StorageReference mStorageRef;
        private DatabaseReference mDatabaseRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_upload_image);

            mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
            mDatabaseRef=FirebaseDatabase.getInstance().getReference();

           mButtonChooseImage=findViewById(R.id.button_choose_image);
           mButtonChooseImage.setOnClickListener(this);
           mButtonUpload=findViewById(R.id.button_upload);
           mButtonUpload.setOnClickListener(this);
           mTextViewShowUploads=findViewById(R.id.text_view_show_uploads);
           mEditTextFileName=findViewById(R.id.edit_text_file_name);
           mImageView=findViewById(R.id.image_view);

        }

        @Override
        public void onClick(View v) {

            int id=v.getId();

            if(id==mButtonChooseImage.getId())
                openFileChooser();
            else if(id==mButtonUpload.getId())
            upLoadFile();



        }

        private String getUriExtention(Uri uri){

            ContentResolver cR=this.getContentResolver();
            MimeTypeMap mime=MimeTypeMap.getSingleton();
           return  mime.getExtensionFromMimeType(cR.getType(uri));
        }

        private void upLoadFile() {

                if(mImageUri!=null){

    StorageReference fileReference=mStorageRef.child(System.currentTimeMillis()+"."+getUriExtention(mImageUri));
    fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            hideProgressDialog();1

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                   mProgressDialog.setProgress(0);
                }
            }, 5000);

            Toast.makeText(UploadImageActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

            Upload upload=new Upload(mEditTextFileName.getText().toString().trim(), taskSnapshot.getUploadSessionUri().toString());
            String uploadID=mDatabaseRef.push().getKey();
            mDatabaseRef.child("user-pics").child(UploadImageActivity.this.dataHolder.getUserId()).child(uploadID).setValue(upload);


        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
        Toast.makeText(UploadImageActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
            showProgressDialog();
      //  double progress=(100*(taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount()));
       // mProgressDialog.setProgress((int) progress);

        }
    });
                }
                else{

                    Toast.makeText(this,"No file selected", Toast.LENGTH_LONG ).show();
                }

        }

        private void openFileChooser() {

            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==PICK_IMAGE_REQUEST&& resultCode==RESULT_OK
                    &&data!=null&&data.getData()!=null){

                mImageUri=data.getData();

              Picasso.with(this).load(mImageUri).into(mImageView);
             // mImageView.setImageURI(mImageUri);
            }
        }
    }
