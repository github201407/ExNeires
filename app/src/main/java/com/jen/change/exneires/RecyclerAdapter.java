package com.jen.change.exneires;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @ClassName RecyclerAdapter
 * @Description TODO
 * @date 2014-7-2
 */
public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder>{
	private String[] mDataset;
	private Context context;

	/**
	 * @Description: TODO
	 */
	public RecyclerAdapter(Context context, String[] dataset) {
		this.mDataset = dataset;
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
//			mTextView.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					Toast.makeText(v.getContext(), mTextView.getText(), Toast.LENGTH_SHORT).show();
//					
//				}
//			});
		}

	}

	/**
	 * @Method: getItemCount
	 * @Description: TODO
	 * @return
	 * @see Adapter#getItemCount()
	 */
	@Override
	public int getItemCount() {
		return mDataset.length;
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
		holder.mImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.splash));
		holder.mTextView.setText(mDataset[position]);
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
