package com.example.onlinedelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
class ShortestPath {

    static final int V = 9;
    static String S="";
    static int len;
    HashMap<String,Integer> hs=new HashMap<String,Integer>();
    int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {

            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }
    void printSolution(int dist[])
    {

        for (int i = 0; i < V; i++) {
            hs.put(i+"",dist[i]);
        }
        Map<Integer, String> map = sortValues(hs);

        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        System.out.println("Path that the vehicle should follow is\n ");
        System.out.print("Out for Delivery-=>   ");
        while(iterator2.hasNext())
        {
            Map.Entry me2 = (Map.Entry)iterator2.next();
            S=S+me2.getKey()+":>";
        }

        S="Delivery Start \n"+S+"\n\nDelivery Completed";
        len= (int) map.values().toArray()[map.size()-1];
    }
    private static HashMap sortValues(HashMap map)
    {
        List list = new LinkedList(map.entrySet());
        //Custom Comparator
        Collections.sort(list, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        //copying the sorted list in HashMap to preserve the iteration order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
    void dijkstra(int graph[][], int src)
    {
        int dist[] = new int[V];


        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {

            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, sptSet);


            sptSet[u] = true;


            for (int v = 0; v < V; v++) {


                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];}
        }


        printSolution(dist);

    }

}



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int graph[][] = new int[][] {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 0);



        Button b=findViewById(R.id.button);
        Button b1=findViewById(R.id.button1);
        TextView text=findViewById(R.id.text);
        Button b2=findViewById(R.id.button3);
        text.setText("What do wanna see the The Original Route or The Shortest Path..!!");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, Animation.class);
                startActivity(intent);
                MainActivity.this.finish();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(" ");
                String kk="Inefficient Route\n\nDelivery Start\n0:>1:>2:>3:>4:>5:>6:>7:>8:>\n\nDelivery Completed";

                text.setText(kk);



            }
        });
       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               text.setText(" ");
                   text.setText("Efficient Route\n\n"+t.S);



           }
       });
    }
}