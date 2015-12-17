package com.jen.change.exneires.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jen.change.exneires.PhotoIntentActivity;
import com.jen.change.exneires.R;
import com.jen.change.exneires.bean.Res;

/**
 * @ClassName RecyclerAdapter
 * @Description TODO
 * @date 2014-7-2
 */
public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder>{
	private String[] mDataset;
	private Context context;
	private Res mRes;

	/**
	 * @Description: TODO
	 */
	public RecyclerAdapter(Context context, Res res) {
		this.mRes = res;
		this.mDataset = res.getImgUrl().split("\\|");
		this.context = context;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		private TextView mTextView;
		private ImageView mImageView;

		/**
		 * @Description: TODO
		 * @param itemView
		 */
		public ViewHolder(View itemView) {
			super(itemView);
			mTextView = (TextView) itemView.findViewById(R.id.text1);
			mImageView = (ImageView)itemView.findViewById(R.id.icon);
			mImageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String tag = (String)v.getTag();
					if(tag.equals("add"))
						doUploadImage();
					else
						Toast.makeText(v.getContext(), mTextView.getText(), Toast.LENGTH_SHORT).show();
				}
			});
		}

	}

	private void doUploadImage() {
		Intent intent = new Intent(this.context, PhotoIntentActivity.class);
		this.context.startActivity(intent);
	}

	/**
	 * @Method: getItemCount
	 * @Description: TODO
	 * @return
	 * @see Adapter#getItemCount()
	 */
	@Override
	public int getItemCount() {
		return mDataset.length + 1;
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
		if(position == mDataset.length){
			holder.mImageView.setImageDrawable(ContextCompat.getDrawable(context, android.R.drawable.ic_menu_add));
			holder.mImageView.setTag("add");
		} else {
			holder.mImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.splash));
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

}
