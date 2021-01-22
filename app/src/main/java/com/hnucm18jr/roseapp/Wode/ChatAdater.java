package com.hnucm18jr.roseapp.Wode;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hnucm18jr.roseapp.DataUtil;
import com.hnucm18jr.roseapp.R;

import java.util.List;

public class ChatAdater extends BaseAdapter {
    private List<ChatMessager> list;

    public int getCount() {
        return list.isEmpty() ? 0 : list.size();

    }

    public ChatAdater(List<ChatMessager> list) {
        super();
        this.list = list;
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回消息类型：0、接受 1、发送
     */
    public int getItemViewType(int position) {
        ChatMessager chatMessager = list.get(position);
        if (chatMessager.getType() == ChatMessager.Type.INCOUNT) {
            return 0;
        }
        return 1;
    }

    public int getViewTypeCount() {
        return 2;

    }

    /**
     * 根据type加载不同布局
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessager chatMessager = list.get(position);
        if (convertView == null) {
            ViewHolder viewHolder = null;
            if (getItemViewType(position) == 0) {
                System.out.println("type" + getItemViewType(position));
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item1, null);
                viewHolder = new ViewHolder();
                viewHolder.time = (TextView) convertView.findViewById(R.id.data1);
                viewHolder.message = (TextView) convertView.findViewById(R.id.message1);

            } else {
                System.out.println("type2" + getItemViewType(position));
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item2_xml, null);
                }

                viewHolder = new ViewHolder();
                viewHolder.time = (TextView) convertView.findViewById(R.id.data2);
                viewHolder.message = (TextView) convertView.findViewById(R.id.message2);

            }
            convertView.setTag(viewHolder);
        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.time.setText(DataUtil2.getDataString(chatMessager.getDate()));
        System.out.println("time" + DataUtil2.getDataString(chatMessager.getDate()));
        vh.message.setText(chatMessager.getMessager());
        System.out.println("text" + chatMessager.getMessager());
        return convertView;
    }

    private class ViewHolder {
        private TextView time, message;
    }

}


