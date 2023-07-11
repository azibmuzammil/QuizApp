package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class QuizResultFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    public QuizResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_item_quiz_result_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        databaseHelper = new DatabaseHelper(requireContext());

        List<QuizResult> quizResults = databaseHelper.getAllQuizResults();
        if (quizResults.isEmpty()) {
            Toast.makeText(requireContext(), "No quiz results available.", Toast.LENGTH_SHORT).show();
        } else {
            QuizResultAdapter quizResultAdapter = new QuizResultAdapter(requireContext(), quizResults);
            recyclerView.setAdapter(quizResultAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseHelper.close();
    }
}