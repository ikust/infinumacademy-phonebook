package co.infinum.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ivan on 16/07/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

    private static final String NAME_FORMAT = "%s %s";

    static class ViewHolder {

        @InjectView(R.id.tv_name)
        TextView nameTextView;

        @InjectView(R.id.tv_number)
        TextView numberTextView;

        @InjectView(R.id.tv_email)
        TextView emailTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem_contact, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTextView.setText(
                String.format(NAME_FORMAT, getItem(position).getFirstName(), getItem(position).getLastName())
        );

        holder.numberTextView.setText(getItem(position).getNumber());
        holder.emailTextView.setText(getItem(position).getMail());

        return convertView;
    }
}
