package com.jen.change.exneires.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jen.change.exneires.R;
import com.jen.change.exneires.activity.SubmitRes;
import com.jen.change.exneires.utils.CameraUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName RecyclerAdapter
 * @Description TODO
 * @date 2014-7-2
 */
public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder>{
	private Context context;
	private ArrayList<String> arrayList = new ArrayList<>();/*图片本地路径*/

	public ArrayList<String> getArrayList() {
		return arrayList;
	}

	/**
	 * @Description: TODO
	 */
	public RecyclerAdapter(Context context){
		this.context = context;
	}

	public void addImage(String imgUrl){
		this.arrayList.add(imgUrl);
		notifyDataSetChanged();
	}

	private void delImage(String imgUrl){
		this.arrayList.remove(imgUrl);
		notifyDataSetChanged();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		private ImageButton mImageButton;
		private TextView mTextView;
		private ImageView mImageView;

		/**
		 * @Description: TODO
		 * @param itemView
		 */
		public ViewHolder(View itemView) {
			super(itemView);
			mImageButton = (ImageButton) itemView.findViewById(R.id.del);
			mTextView = (TextView) itemView.findViewById(R.id.text1);
			mImageView = (ImageView)itemView.findViewById(R.id.icon);
			mImageButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String url = mTextView.getText().toString().trim();
					if(arrayList.contains(url))
						delImage(url);
				}
			});
			mImageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String tag = (String) v.getTag();
					if (tag.equals("add")) {
						doUploadImage();
					} else {
						String url = mTextView.getText().toString().trim();
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
						v.getContext().startActivity(intent);
//						Toast.makeText(v.getContext(), mTextView.getText(), Toast.LENGTH_SHORT).show();
					}
				}
			});
		}

	}

	public final static int ACTION_TAKE_PHOTO = 1;
	public final static int ACTION_PICK_PHOTO = 2;
	private void doUploadImage() {
		showMenuDialog();
	}

	private void showMenuDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("照片来源于：");
		final CharSequence[] items = {"相册", "拍照"};
		builder.setItems(items
				, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
					case 0:
						dispatchPickPictureIntent(ACTION_PICK_PHOTO);
						break;
					case 1:
						dispatchTakePictureIntent(ACTION_TAKE_PHOTO);
						break;
					default:
						break;
				}
				dialog.dismiss();
			}
		});
		AlertDialog alter = builder.create();
		alter.show();
	}

	/**
	 * @Method: getItemCount
	 * @Description: TODO
	 * @return
	 * @see Adapter#getItemCount()
	 */
	@Override
	public int getItemCount() {
		return arrayList.size() + 1;
	}

	/**
	 * @Method: onBindViewHolder
	 * @Description: TODO
	 * @param holder
	 * @param position
	 * @see Adapter#onBindViewHolder(RecyclerView.ViewHolder,
	 *      int)
	 */
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if(position == arrayList.size()){
			holder.mImageView.setImageDrawable(ContextCompat.getDrawable(context, android.R.drawable.ic_menu_add));
			holder.mImageView.setTag("add");
			holder.mImageButton.setVisibility(View.INVISIBLE);
		} else {
			holder.mImageButton.setVisibility(View.VISIBLE);
//			holder.mImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.splash));
			String url = arrayList.get(position);
			CameraUtil.setPic(holder.mImageView, url);
			holder.mTextView.setText(url);
			holder.mImageView.setTag("image");
		}
	}

	/**
	 * @Method: onCreateViewHolder
	 * @Description: TODO
	 * @param parent
	 * @param viewType
	 * @return
	 * @see Adapter#onCreateViewHolder(ViewGroup,
	 *      int)
	 */
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = View.inflate(parent.getContext(), R.layout.recycler_item, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	private void dispatchPickPictureIntent(int actionCode) {
		Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
		getIntent.setType("image/*");

		Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		pickIntent.setType("image/*");

		Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
		chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

		((SubmitRes)this.context).startActivityForResult(chooserIntent, actionCode);
	}
	private void dispatchTakePictureIntent(int actionCode) {
		CameraUtil cameraUtil = CameraUtil.getInstance();
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		switch(actionCode) {
			case ACTION_TAKE_PHOTO:
				File f = null;
				try {
					f = cameraUtil.setUpPhotoFile();
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
				} catch (IOException e) {
					e.printStackTrace();
					f = null;
					cameraUtil.setmCurrentPhotoPath("");
				}
				break;
			default:
				break;
		} // switch
		((SubmitRes)this.context).startActivityForResult(takePictureIntent, actionCode);
	}

}
