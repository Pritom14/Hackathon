/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.todolist;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.todolist.data.TaskContract;


public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = AddTaskActivity.class.getSimpleName();

    // Declare a member variable to keep track of a task's selected mPriority


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: activity starting");
        setContentView(R.layout.activity_add_task);

        // Initialize to highest mPriority by default (mPriority = 1)

        Log.d(TAG, "onCreate: activity created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: activity becoming visible");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: activity in foreground");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: activity losing foreground focus");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: activity no longer visible");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: activity being destroyed");
    }


    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddTask(View view) {
        Log.d(TAG, "onClickAddTask: ADD button clicked");
        // Not yet implemented
        // Check if EditText is empty, if not retrieve input and store it in a ContentValues object
        // If the EditText input is empty -> don't create an entry
        String input_username = ((EditText) findViewById(R.id.username)).getText().toString();
        String input_department= ((EditText) findViewById(R.id.add_department)).getText().toString();
        TextView priorityTextView=(TextView)findViewById(R.id.priorityTextView);
        //priorityTextView.setText(input_department);
        if (input_username.length() == 0&&input_department.length()==0) {
            Log.d(TAG, "onClickAddTask: username and department are empty, aborting insert");
            return;
        }

        // Insert new task data via a ContentResolver
        // Create new empty ContentValues object
        ContentValues contentValues = new ContentValues();
        // Put the task description and selected mPriority into the ContentValues
        contentValues.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, input_username);
        contentValues.put(TaskContract.TaskEntry.COLUMN_PRIORITY, input_department);
       // contentValues.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, String.valueOf(priorityTextView));
        // Insert the content values via a ContentResolver/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/
        Log.d(TAG, "onClickAddTask: inserting new task via ContentResolver");
        Uri uri = getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, contentValues);

        // Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if(uri != null) {
            Log.d(TAG, "onClickAddTask: insert succeeded, uri=" + uri.toString());
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        } else {
            Log.e(TAG, "onClickAddTask: insert failed, ContentResolver returned null uri");
        }

        // Finish activity (this returns back to MainActivity)
        Log.d(TAG, "onClickAddTask: finishing activity");
        finish();

    }




}
