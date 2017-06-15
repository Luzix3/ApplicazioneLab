package com.example.lucia.applicazionelab.MainETab;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lucia.applicazionelab.Database.Libro;

import com.example.lucia.applicazionelab.R;
import com.example.lucia.applicazionelab.Registrazione.Page2reg;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

@SuppressWarnings("deprecation")
public class AddLibro extends ActionBarActivity {

    private static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE= 2;
    Button btnAdd;
    EditText Editname;
    EditText EditAutor;
    EditText EditAnn;
    EditText EditCode;
    EditText EditTrama;
    EditText EditGener;
    Button SelectImage;
    ImageView img;
    int PICK_IMAGE_REQUEST = 111;
    Uri filePath;
    ProgressDialog pd;

    //creating reference to firebase storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://biblapp-432f7.appspot.com/");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_libro);

        SelectImage = (Button)findViewById(R.id.buttonImmagine);
        btnAdd = (Button)findViewById(R.id.buttonProcedi3);
        Editname = (EditText)findViewById(R.id.editName);
        EditAutor = (EditText)findViewById(R.id.editAutor);
        EditAnn = (EditText)findViewById(R.id.editAnn);
        EditCode = (EditText)findViewById(R.id.editCode);
        EditTrama = (EditText)findViewById(R.id.editTrama);
        EditGener = (EditText)findViewById(R.id.editGener);
        img= (ImageView)findViewById(R.id.imageScelta);

        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");

        SelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                final String name1 = Editname.getText().toString();
                final String autor1 = EditAutor.getText().toString();
                final String gener1 = EditGener.getText().toString();
                final String code = EditCode.getText().toString();
                final String anno1 = EditAnn.getText().toString();




                if(filePath != null) {
                    pd.show();

                    StorageReference childRef = storageRef.child("image.jpg");

                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (shouldShowRequestPermissionRationale(
                                Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            // Explain to the user why we need to read the contacts
                        }

                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                        // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                        // app-defined int constant that should be quite unique

                        return;
                    }
                    //uploading the image
                    UploadTask uploadTask = childRef.putFile(filePath);


                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(AddLibro.this, "Upload avvenuto con successo!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(AddLibro.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(AddLibro.this, "Seleziona un'immagine", Toast.LENGTH_SHORT).show();
                }




                if (anno1.isEmpty()) {
                    EditAnn.setError(getString(R.string.obbligatorio));

                }


                if (name1.isEmpty()) {
                    Editname.setError(getString(R.string.obbligatorio));

                }

                if (autor1.isEmpty()) {
                    EditAutor.setError(getString(R.string.obbligatorio));

                }

                if (gener1.isEmpty()) {
                    EditGener.setError(getString(R.string.obbligatorio));

                }

                if (code.isEmpty()) {
                    EditCode.setError(getString(R.string.obbligatorio));

                }

                if (!name1.isEmpty() && !autor1.isEmpty() && !gener1.isEmpty() && !code.isEmpty() && !anno1.isEmpty())
                {




                    DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference("libri");
                    //ora creo un nodo user che mi ritorna la chiave unica dell'user (quella che solo questa app può avere

                    String BookId = mDatabase1.push().getKey();
                    // creo un oggetto della classe utente
                    Libro l1 = new Libro(autor1, code, name1, anno1, gener1,filePath);
                    // metto l'user nel database sotto il nodo Utenti con la sua chiave unica
                    mDatabase1.child(BookId).setValue(l1);

                    Intent openPageSucces2= new Intent(AddLibro.this, MainActivity.class);
                    startActivity(openPageSucces2);






                }
            }


        });

                }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                img.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


            }