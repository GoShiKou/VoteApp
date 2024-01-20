package jp.ac.ecc.se.voteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VoteOptionAdapter extends ArrayAdapter<VoteOption> {
    private int optionsCount;
    private ArrayList<String> retrievedList;

    public VoteOptionAdapter(@NonNull Context context, List<VoteOption> voteOptions, int optionsCount, ArrayList<String> retrievedList) {
        super(context, 0, optionsCount);
        this.optionsCount = optionsCount;
        this.retrievedList = retrievedList;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VoteOption voteOption = getItem(position);

        if (convertView == null) {
            // if convertView null，create new View
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        TextView text1 = convertView.findViewById(R.id.text1);
        TextView text2 = convertView.findViewById(R.id.text2);

        text1.setText(retrievedList.get(position));

        if (voteOption.isResultDisplayed()) {
            // vote fro each option
//            for (int i = 0; i < optionsCount; i++) {
//                votesText.append("Option ").append(i + 1).append(": ").append(voteOption.getVotes(i)).append(" ");
//            }
//            text2.setText(votesText.toString());
            text2.setText(String.valueOf(voteOption.getVotes(position)));
            convertView.setEnabled(false);
        } else {
            text2.setText("");
            convertView.setEnabled(!voteOption.hasVoted());
        }

        return convertView;
    }
}

