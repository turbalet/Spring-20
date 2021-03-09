package kz.edu.mapreduce;

import kz.edu.model.Vote;
import kz.edu.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
@Component
public class MapReduce {

    private final VoteService voteService;

    private static boolean isReady;

    public Map<Long, Integer> voteCount;

    private void writeToFile(List<Vote> votes) throws IOException {
        File fout = new File("C:\\Users\\acer\\IdeaProjects\\Spring-20\\src\\main\\java\\kz\\edu\\data\\data.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (Vote vote : votes) {
            bw.write("" + vote.getQuestion().getId());
            bw.newLine();
        }
        bw.close();
    }

    public synchronized void produce() throws InterruptedException, IOException {
        Thread.sleep(100);
        writeToFile(voteService.getAll());
        isReady = true;
        notify();
    }


    public synchronized void consume() throws InterruptedException {
        if (!isReady) wait();
        readFile();

    }

    public void readFile(){
        try
        {
            FileInputStream fis=new FileInputStream("C:\\Users\\acer\\IdeaProjects\\Spring-20\\src\\main\\java\\kz\\edu\\data\\data.txt");
            Scanner sc=new Scanner(fis);
            while(sc.hasNextLine()) {
                long questionId = sc.nextLong();
                voteCount.merge(questionId, 1, Integer::sum);
            }
            sc.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


}


