package ru.geekbrains.spring.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String name;
    private  int score;

    public void setScore(int score) {
       if(score>0&&score<=100) {
           this.score = score;
       }
       else score=0;
    }

    public void incScore(){
      if (score<100){
           score++;
       }
    }
    public void decScore(){
       if(score>0) {
           score--;
       }
    }
}
