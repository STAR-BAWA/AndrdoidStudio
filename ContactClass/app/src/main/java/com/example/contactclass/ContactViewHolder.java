package com.example.contactclass;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
class ContactViewHolder extends RecyclerView.ViewHolder {
   TextView tvName, tvPhoneNum;
   ImageView deleteContact;
   ImageView editContact;
   ContactViewHolder(View itemView) {
      super(itemView);
      tvName = itemView.findViewById(R.id.contactName);
      tvPhoneNum = itemView.findViewById(R.id.phoneNum);
      deleteContact = itemView.findViewById(R.id.deleteContact);
      editContact = itemView.findViewById(R.id.editContact);
   }
}