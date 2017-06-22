package vikesh8860.calculate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graph_activity extends AppCompatActivity {
    double[] amount,interest;
    int duration;
    String dur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_activity);

        Bundle bundle = getIntent().getExtras();
        amount = bundle.getDoubleArray("Loan_amount");
        interest = bundle.getDoubleArray("Loan_interest");
        dur = bundle.getString("Loan_duration");
        duration = Integer.parseInt(dur);
        int pos = bundle.getInt("x_axis");
        String pos_string="";
        if(pos==0)  pos_string="Days";
        if(pos==1)  pos_string="Months";
        if(pos==2)  pos_string="Years";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setNavigationBarColor(Color.parseColor("#004D40"));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    onBackPressed();
            }
        });
        if(amount[duration]>1000000000000000.0) {
            Toast.makeText(this, "Sorry!! The data is too large to be displayed", Toast.LENGTH_SHORT).show();
        }
        else {
            GraphView graph = (GraphView) findViewById(R.id.graph_view);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data(0));
            graph.addSeries(series);
            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            graph.getLegendRenderer().setVisible(true);
            graph.getGridLabelRenderer().setHorizontalAxisTitle(""+pos_string);
            graph.getGridLabelRenderer().setVerticalAxisTitle("Amount (or Interest)");
            graph.getLegendRenderer().setBackgroundColor(Color.parseColor("#F5F5F5"));
            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(data(1));
            graph.addSeries(series2);
            series.setTitle("Amount");
            series2.setTitle("Interest");
            series.setColor(Color.parseColor("#000000"));
            series2.setColor(Color.parseColor("#00BFA5"));
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);
            graph.getViewport().setScrollable(true); // enables horizontal scrolling
            graph.getViewport().setScrollableY(true); // enables vertical scrolling
            graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
            graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
        }
    }
    public DataPoint[] data(int m){
        int n=duration;     //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        if(m==0){       //amount
            for(int i=0;i<n;i++){
                DataPoint v = new DataPoint(i,amount[i]);
                values[i] = v;
            }
        }
        else {
            for(int i=0;i<n;i++){
                DataPoint v = new DataPoint(i,interest[i]);
                values[i] = v;
            }
        }
        return values;
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }
}
