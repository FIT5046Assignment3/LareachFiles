package com.fit.quizcrafter.domain.room;

import androidx.room.TypeConverter;

import com.fit.quizcrafter.domain.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class QuestionConverter {

    @TypeConverter
    public String objectToString(List<Question> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public List<Question> stringToObject(String json) {
        Type listType = new TypeToken<List<Question>>() {
        }.getType();
        return new Gson().fromJson(json, listType);

    }
}
