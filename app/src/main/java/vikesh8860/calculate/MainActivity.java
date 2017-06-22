package vikesh8860.calculate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //For the BMI Window
    private RadioGroup rg;
    int age2 = 0, age3 = 0, height2 = 0, height3 = 0, weight2 = 0, weight3 = 0, gender = -1, t1 = 0, t2 = 0, t3 = 0, t4 = 0;
    float temp1 = 0, temp2 = 0, temp3 = 0;
    String age1 = "", height1 = "", weight1 = "";
    EditText age, height, weight;
    // For the bmi window


    // For the Unit Converter Window
    Spinner conversion_type_spinner, from_spinner, to_spinner;
    String from_spinner_string, to_spinner_string, choice_spinner;
    int from_spinner_position, to_spinner_position, choice_spinner_pos;
    ArrayAdapter<CharSequence> adapter_area, adapter_volume, adapter_digital_storage, adapter_energy,           // Create All the array adapter for different type of conversions
            adapter_frequency, adapter_mass, adapter_length, adapter_plane_angle, adapter_pressure, adapter_speed, adapter_temperature, adapter_time;
    EditText editText_from;
    TextView textView_to;
    BigDecimal[][] array = {{new BigDecimal("1"), new BigDecimal("1000000.0"), new BigDecimal("1195990.05"), new BigDecimal("10763910.4"), new BigDecimal("1550000000"), new BigDecimal("0.386102"), new BigDecimal("100"), new BigDecimal("247.105")},
            {new BigDecimal("1"), new BigDecimal("0.125"), new BigDecimal("0.000122073125"), new BigDecimal("0.00000011920992"), new BigDecimal("0.0000000001164153"), new BigDecimal("0.0000000000001136868377"),new BigDecimal("0.000000000000001110223024")},
            {new BigDecimal("1"), new BigDecimal("0.001"), new BigDecimal("0.000239006"), new BigDecimal("0.00000027778"), new BigDecimal("0.737562")},
            {new BigDecimal("1"), new BigDecimal("0.001"), new BigDecimal("0.000001"), new BigDecimal("0.000000001")},
            {new BigDecimal("1"), new BigDecimal("1000"), new BigDecimal("1000000"), new BigDecimal("1000000000"), new BigDecimal("1000000000000"), new BigDecimal("157.473"), new BigDecimal("2204.62"), new BigDecimal("35274")},
            {new BigDecimal("1"), new BigDecimal("1000"), new BigDecimal("100000"), new BigDecimal("10000000000"),new BigDecimal("10000000000000"), new BigDecimal("0.621371"), new BigDecimal("1093.61"), new BigDecimal("3280.84"), new BigDecimal("39370.1"), new BigDecimal("0.539953")},
            {new BigDecimal("1"), new BigDecimal("1.1111111300619"), new BigDecimal("0.0174533"), new BigDecimal("17.453300")},
            {new BigDecimal("1"), new BigDecimal("1.01325"), new BigDecimal("101325"), new BigDecimal("760")},
            {new BigDecimal("1"), new BigDecimal("1.46667"), new BigDecimal("0.44704"), new BigDecimal("1.60934"), new BigDecimal("0.868976")},
            {},  // keep blank for temperature
            {new BigDecimal("1"), new BigDecimal("0.001"), new BigDecimal("0.000016667"), new BigDecimal("0.00000027778"), new BigDecimal("0.000000011574"), new BigDecimal("0.0000000016534"), new BigDecimal("0.00000000038052"), new BigDecimal("0.00000000003171"), new BigDecimal("0.000000000003171"), new BigDecimal("0.0000000000003171")},
            {new BigDecimal("1"), new BigDecimal("1000"), new BigDecimal("0.219969"), new BigDecimal("0.879877"), new BigDecimal("3.51951"), new BigDecimal("0.0353147"), new BigDecimal("61.0237"), new BigDecimal("61023.7")}};
    String val = "0";
    //   For the unit converter


    //For the interest calculator
    Button show_button;
    Spinner cycle,duration;
    String loan_amount="",loan_interest="",loan_duration="";
    int spinner_cycle_pos=0,spinner_duration_pos=0;
    TextView interest_t,amount_t;
    EditText loan_edit_amount,loan_edit_interest,loan_edit_duration;
    public static double[] loan_points_amount = new  double[25000];
    public static double[] loan_points_interest = new  double[25000];
    BigDecimal loan_amount_big,loan_interest_big,loan_duration_big;
    //For the interest calculator

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bmi:
                    setTitle(" BMI Calculator");    // To change the title of the action bar

                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));    // To change the color of the action Bar
                    getWindow().setStatusBarColor(Color.parseColor("#00796B"));
                    getWindow().setNavigationBarColor(Color.parseColor("#004D40"));

                    change_layout(0);
                    //bmi_calculator();
                    return true;

                case R.id.navigation_unit:

                    setTitle(" Unit Converter");    // To change the title of the action bar
                    // To change the color of the action Bar
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#607D8B")));
                    getWindow().setStatusBarColor(Color.parseColor("#455A64"));
                    getWindow().setNavigationBarColor(Color.parseColor("#263238"));

                    change_layout(1);
                    unit_converter();

                    return true;
                case R.id.navigation_interest:
                    // To change the title of the action bar
                    setTitle(R.string.title_activity_interest);
                    // To change the color of the action Bar
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
                    getWindow().setStatusBarColor(Color.parseColor("#303F9F"));
                    getWindow().setNavigationBarColor(Color.parseColor("#1A237E"));
                    change_layout(2);
                    interest_calculator();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initially set the BMI the main homepage
        setTitle("BMI Calculator");
        // To change the color of the action Bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        getWindow().setStatusBarColor(Color.parseColor("#00796B"));
        getWindow().setNavigationBarColor(Color.parseColor("#004D40"));


        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.rg);

        //To remove the focus from the activity when the activity starts
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void change_layout(int selectedItem) {
        // get your outer Frame layout
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content);
        View view;
        if (selectedItem == 0) {
            //Add the content bmi layout to Inflator
            view = getLayoutInflater().inflate(R.layout.content_bmi, frameLayout, false);
        } else if (selectedItem == 1) {
            //Add the content bmi layout to Inflator
            view = getLayoutInflater().inflate(R.layout.content_unit, frameLayout, false);
        } else {
            //Add the content bmi layout to Inflator
            view = getLayoutInflater().inflate(R.layout.content_interest, frameLayout, false);
        }
        frameLayout.removeAllViews();
        frameLayout.addView(view);
    }

    private void initialise_adapters() {
        // Create All the array adapter for different type of conversions
        adapter_area = ArrayAdapter.createFromResource(getApplication(), R.array.area, android.R.layout.simple_spinner_item);
        adapter_digital_storage = ArrayAdapter.createFromResource(getApplication(), R.array.digital_storage, android.R.layout.simple_spinner_item);
        adapter_energy = ArrayAdapter.createFromResource(getApplication(), R.array.energy, android.R.layout.simple_spinner_item);
        adapter_frequency = ArrayAdapter.createFromResource(getApplication(), R.array.frequency, android.R.layout.simple_spinner_item);
        adapter_mass = ArrayAdapter.createFromResource(getApplication(), R.array.mass, android.R.layout.simple_spinner_item);
        adapter_length = ArrayAdapter.createFromResource(getApplication(), R.array.length, android.R.layout.simple_spinner_item);
        adapter_plane_angle = ArrayAdapter.createFromResource(getApplication(), R.array.phase_angle, android.R.layout.simple_spinner_item);
        adapter_pressure = ArrayAdapter.createFromResource(getApplication(), R.array.pressure, android.R.layout.simple_spinner_item);
        adapter_speed = ArrayAdapter.createFromResource(getApplication(), R.array.speed, android.R.layout.simple_spinner_item);
        adapter_temperature = ArrayAdapter.createFromResource(getApplication(), R.array.temperature, android.R.layout.simple_spinner_item);
        adapter_time = ArrayAdapter.createFromResource(getApplication(), R.array.time, android.R.layout.simple_spinner_item);
        adapter_volume = ArrayAdapter.createFromResource(getApplication(), R.array.volume, android.R.layout.simple_spinner_item);

    }

    // Main method of the unit converter
    private void unit_converter() {
        // Initialise the main spinner
        conversion_type_spinner = (Spinner) findViewById(R.id.conversion_type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplication(), R.array.conversion_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        conversion_type_spinner.setAdapter(adapter);
        conversion_type_spinner.setOnItemSelectedListener(new spinner());

        from_spinner = (Spinner) findViewById(R.id.from_spinner);
        to_spinner = (Spinner) findViewById(R.id.to_spinner);

        initialise_adapters();

        //initally set default to  area adapter
        adapter_area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from_spinner.setAdapter(adapter_area);
        to_spinner.setAdapter(adapter_area);
        from_spinner_string = "Square kilometer";
        to_spinner_string = "Square kilometer";
        choice_spinner = "Area";

        //Default values
        from_spinner_position = 0;
        to_spinner_position = 0;
        choice_spinner_pos = 0;

        from_spinner.setOnItemSelectedListener(new spinner());
        to_spinner.setOnItemSelectedListener(new spinner());


        editText_from = (EditText) findViewById(R.id.from_edit);
        textView_to = (TextView) findViewById(R.id.to_text);
        editText_from.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                val = s.toString();
                int flag = 0;
                if (val.equals("")) {
                    flag = 1;
                    val = "0";
                }
                if (val.equals("-") || val.equals(".")) flag = 1;
                if (choice_spinner.equals("Temperature")) {
                    if (flag == 0) {
                        double celcius = Double.parseDouble(val);
                        if (from_spinner_position == 2) {
                            celcius = celcius - Double.parseDouble("32");
                            celcius = celcius * Double.parseDouble("0.55555");
                        } else if (from_spinner_position == 1)
                            celcius = celcius - Double.parseDouble("273.15");

                        if (to_spinner_position == 2) {
                            celcius = celcius * Double.parseDouble("1.8");
                            celcius = celcius + Double.parseDouble("32");
                        } else if (to_spinner_position == 1)
                            celcius = celcius + Double.parseDouble("273.15");
                        print_exponent(textView_to,celcius);
                    }
                    else {
                        textView_to.setText("");
                    }

                } else {
                    if(flag==0){
                        double temp = Double.parseDouble(val);
                        temp = temp/Double.parseDouble(array[choice_spinner_pos][from_spinner_position].toString());
                        temp = temp * Double.parseDouble(array[choice_spinner_pos][to_spinner_position].toString());
                        temp = temp/Double.parseDouble(array[choice_spinner_pos][0].toString());
                        print_exponent(textView_to,temp);
                    }
                    else{
                        textView_to.setText("");
                    }
                }
            }
        });

    }

    //Method to print the numbers in exponent form
    public void print_exponent(TextView view,double temp) {
        String temp1 = "" + temp;
        String temp2 = "";
        int bds = 0;
        for (char c : temp1.toCharArray()) {
            if (c == 'E') {
                temp2 += " e ";
                bds = 1;
            } else {
                if (bds == 1) {
                    if (c == '-')
                        temp2 += c;
                    else
                        temp2 = temp2 + "+" + c;
                } else
                    temp2 += c;
                bds = 0;
            }
        }
        view.setText(temp2);
    }

    //This method takes a big decimal number and convert that to the exponent from and scale is mantissa
    private static String format(BigDecimal x, int scale) {
        NumberFormat formatter = new DecimalFormat("0.0E0");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        formatter.setMinimumFractionDigits(scale);
        return formatter.format(x);
    }


    //Spinner class to select spinner and also add gender
    class spinner implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            if (parent.getId() == R.id.conversion_type) {

                editText_from.getText().clear();
                textView_to.setText("");
                choice_spinner = parent.getItemAtPosition(position).toString();
                choice_spinner_pos = position;
                from_spinner.setEnabled(true);
                to_spinner.setEnabled(true);
                if (choice_spinner.equals("Area") == true) {
                    adapter_area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_area);
                    to_spinner.setAdapter(adapter_area);
                } else if (choice_spinner.equals("Digital Storage") == true) {
                    adapter_digital_storage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_digital_storage);
                    to_spinner.setAdapter(adapter_digital_storage);
                } else if (choice_spinner.equals("Energy") == true) {
                    adapter_energy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_energy);
                    to_spinner.setAdapter(adapter_energy);
                } else if (choice_spinner.equals("Frequency") == true) {
                    adapter_frequency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_frequency);
                    to_spinner.setAdapter(adapter_frequency);
                } else if (choice_spinner.equals("Mass") == true) {
                    adapter_mass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_mass);
                    to_spinner.setAdapter(adapter_mass);
                } else if (choice_spinner.equals("Length") == true) {
                    adapter_length.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_length);
                    to_spinner.setAdapter(adapter_length);
                } else if (choice_spinner.equals("Plane Angle") == true) {
                    adapter_plane_angle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_plane_angle);
                    to_spinner.setAdapter(adapter_plane_angle);
                } else if (choice_spinner.equals("Pressure") == true) {
                    adapter_pressure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_pressure);
                    to_spinner.setAdapter(adapter_pressure);
                } else if (choice_spinner.equals("Speed") == true) {
                    adapter_speed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_speed);
                    to_spinner.setAdapter(adapter_speed);
                } else if (choice_spinner.equals("Temperature") == true) {
                    adapter_temperature.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_temperature);
                    to_spinner.setAdapter(adapter_temperature);
                } else if (choice_spinner.equals("Time") == true) {
                    adapter_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_time);
                    to_spinner.setAdapter(adapter_time);
                } else if (choice_spinner.equals("Volume") == true) {
                    adapter_volume.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapter_volume);
                    to_spinner.setAdapter(adapter_volume);
                }
            } else if (parent.getId() == R.id.from_spinner) {
                editText_from.getText().clear();
                textView_to.setText("");
                from_spinner_string = parent.getItemAtPosition(position).toString();
                from_spinner_position = position;

            } else if (parent.getId() == R.id.to_spinner) {
                editText_from.getText().clear();
                textView_to.setText("");
                to_spinner_string = parent.getItemAtPosition(position).toString();
                to_spinner_position = position;
            }
            else if(parent.getId()==R.id.duration_spinner){
                spinner_duration_pos = position;
            }
            else if(parent.getId()==R.id.cycle_spinner){
                spinner_cycle_pos = position;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //When nothing is selected
            Toast.makeText(getApplicationContext(), "Please Select a category", Toast.LENGTH_SHORT).show();
            from_spinner.setEnabled(false);
            to_spinner.setEnabled(false);
        }
    }

    public void clear_unit(View view) {
        editText_from.setText("");
        textView_to.setText("");
        val ="";
    }


    //Used to add some time so that user cannot directly press and exity out of the activity
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 4000);
    }

    // For the interest calculator
    private void interest_calculator(){
        // Initialise the duration spinner
        duration = (Spinner) findViewById(R.id.duration_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_duration = ArrayAdapter.createFromResource(getApplication(), R.array.duration, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_duration.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        duration.setAdapter(adapter_duration);
        duration.setOnItemSelectedListener(new spinner());

        // Initialise the cycle spinner
        cycle = (Spinner) findViewById(R.id.cycle_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_cycle = ArrayAdapter.createFromResource(getApplication(), R.array.cycle, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_cycle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        cycle.setAdapter(adapter_cycle);
        cycle.setOnItemSelectedListener(new spinner());

        loan_edit_amount = (EditText)findViewById(R.id.loan_principle);
        loan_edit_interest = (EditText)findViewById(R.id.interest_rate);
        loan_edit_duration = (EditText)findViewById(R.id.duration);

        interest_t  =(TextView) findViewById(R.id.interest_result_interest);
        amount_t=(TextView) findViewById(R.id.interest_result_amount);

        show_button = (Button) findViewById(R.id.show_graph);
        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loan_edit_interest.getText().toString().equals("") == false && loan_edit_duration.getText().toString().equals("") == false && loan_edit_amount.getText().toString().equals("") == false) {
                    Bundle bundle = new Bundle();
                    bundle.putDoubleArray("Loan_amount", loan_points_amount);
                    bundle.putDoubleArray("Loan_interest", loan_points_interest);
                    bundle.putString("Loan_duration", loan_duration);
                    bundle.putInt("x_axis", spinner_duration_pos);
                    Intent intent = new Intent(MainActivity.this, Graph_activity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Please enter the complete details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void result_interest(View view){
        loan_amount = loan_edit_amount.getText().toString();
        loan_interest = loan_edit_interest.getText().toString();
        loan_duration = loan_edit_duration.getText().toString();

        if(!loan_amount.equals("")  && !loan_interest.equals("") && loan_duration.equals("")){
            double d1  =Double.parseDouble(loan_amount),d2 = Double.parseDouble(loan_interest),d3 = Double.parseDouble(loan_duration);
            if(spinner_cycle_pos ==0){          //Simple interest
                d1  = d1 * d2 ;
                if(spinner_duration_pos==0){
                    for(int i=0;i<=((int)d3);i++)
                    {
                        loan_points_interest[i] = (d1*d2*i)/(36520);
                        loan_points_amount[i]   = Double.parseDouble(loan_amount)+loan_points_interest[i];
                    }
                    d1 = (d1/36520)*d3;
                } else if (spinner_duration_pos == 1) {
                    for(int i=0;i<=(int)d3;i++)
                    {
                        loan_points_interest[i] = (d1*d2*i)/(1200);
                        loan_points_amount[i]   = Double.parseDouble(loan_amount)+loan_points_interest[i];
                    }
                    d1 = (d1/1200)*d3;
                }
                else{
                    for(int i=0;i<=(int)d3;i++)
                    {
                        loan_points_interest[i] = (d1*d2*i)/(100);
                        loan_points_amount[i]   = Double.parseDouble(loan_amount)+loan_points_interest[i];
                    }
                }
                print_exponent(interest_t,d1);
                print_exponent(amount_t,d1+Double.parseDouble(loan_amount));
            }
            else {
                if (spinner_cycle_pos == 1) d2 = (1 + d2 / 1200);
                else if (spinner_cycle_pos == 2) d2 = (1 + d2 / 400);
                else if (spinner_cycle_pos == 3) d2 = (1 + d2 / 200);
                else if (spinner_cycle_pos == 4) d2 = (1 + d2 / 100);

                if (spinner_cycle_pos == 1) {
                    if (spinner_duration_pos == 0)
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i / 30.41);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 / 30.41);
                    }

                    else if (spinner_duration_pos == 2)
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i *12);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 * 12);
                    }

                    else {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3);
                    }
                }
                else if (spinner_cycle_pos == 2) {
                    if (spinner_duration_pos == 0)
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i/91.23);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 / 91.23);
                    }
                    else if (spinner_duration_pos == 2)
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i*4);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 * 4);
                    }
                    else
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i/3);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 / 3);
                    }
                }
                else if (spinner_cycle_pos == 3) {
                    if (spinner_duration_pos == 0)
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i/182.5);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 / 182.5);
                    }
                    else if (spinner_duration_pos == 2)
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i*2);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 * 2);
                    }
                    else
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i/6);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 / 6);
                    }
                }
                else if (spinner_cycle_pos == 4) {
                    if (spinner_duration_pos == 0)
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i/365.2);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 / 365.2);
                    }
                    else if (spinner_duration_pos == 2)
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3);
                    }
                    else
                    {
                        for(int i=0;i<=d3;i++)
                        {
                            loan_points_amount[i] = d1 * Math.pow(d2, i/12);
                            loan_points_interest[i] = loan_points_amount[i] - Double.parseDouble(loan_amount);
                        }
                        d1 = d1 * Math.pow(d2, d3 / 12);
                    }
                }
                print_exponent(interest_t, d1 - Double.parseDouble(loan_amount));
                print_exponent(amount_t, d1);
            }
        }
        else{
            Toast.makeText(MainActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
        }
    }

    public  void reset_interest(View view){
        loan_edit_amount.setText("");
        loan_edit_interest.setText("");
        loan_edit_duration.setText("");
        interest_t.setText("");
        amount_t.setText("");
        cycle.setSelection(0);
        duration.setSelection(0);
        loan_amount="";loan_interest="";loan_duration="";
        spinner_cycle_pos=0;spinner_duration_pos=0;
        for(int i=0;i<1000;i++) loan_points_interest[i] =loan_points_amount[i]= 0;
        loan_amount_big=loan_interest_big=loan_duration_big=new BigDecimal("0");
    }

    //Interest calculator

    public void check(View view) {
        int checkedId = rg.getCheckedRadioButtonId();
        if (R.id.rb1 == checkedId) {
            Toast.makeText(this, "Men", Toast.LENGTH_SHORT).show();
            gender = 0;
        } else if (R.id.rb2 == checkedId) {
            Toast.makeText(this, "Women", Toast.LENGTH_SHORT).show();
            gender = 1;
        }
    }

    private float bmiCalculator(int a, int b) {
        if (b == 0 || a == 0) {
            Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show();
            return 0;
        }
        double temp = (b * 10000.0) / (a * a * 1.0);
        float f = (float) temp;
        return f;
    }

    //take age from user
    private int ageit() {
        t1 = 1;
        age = (EditText) findViewById(R.id.age);
        age1 = age.getText().toString();
        try {
            age2 = Integer.parseInt(age1);
        } catch (NumberFormatException e) {
            // Toast.makeText(this,"Only Digits Allowed",Toast.LENGTH_SHORT).show();
        }
        return age2;
    }

    //Take height from user
    private int heightit() {
        t2 = 1;
        height = (EditText) findViewById(R.id.height);
        height1 = height.getText().toString();
        try {
            height2 = Integer.parseInt(height1);
        } catch (NumberFormatException e) {
            // Toast.makeText(this,"Only Digits Allowed",Toast.LENGTH_SHORT).show();
        }
        return height2;
    }

    //take weight from user
    private int weightit() {
        t3 = 1;
        weight = (EditText) findViewById(R.id.weight);
        weight1 = weight.getText().toString();
        try {
            weight2 = Integer.parseInt(weight1);
        } catch (NumberFormatException e) {
            //Toast.makeText(this,"Only Digits Allowed",Toast.LENGTH_SHORT).show();
        }
        return weight2;
    }

    private void showdatamen(float f, int a) {
        TextView under = (TextView) findViewById(R.id.underweight);
        TextView normal = (TextView) findViewById(R.id.normal);
        TextView over = (TextView) findViewById(R.id.overweight);
        TextView obese = (TextView) findViewById(R.id.obese);
        TextView morobese = (TextView) findViewById(R.id.morobese);
        TextView tv = (TextView) findViewById(R.id.result);
        if (a < 10) {
            under.setText("< 14.6");
            normal.setText("14.6 - 21.3");
            over.setText("21.3 - 25.0");
            obese.setText("> 25.0");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 14.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 14.6 && f < 21.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 21.3 && f < 25.0) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#fff000"));
            } else if (f >= 25.0) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 10 && a < 15) {
            under.setText("< 16.7");
            normal.setText("16.7 - 22.5");
            over.setText("22.5 - 25.6");
            obese.setText("> 25.6");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 16.7) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 16.7 && f < 22.5) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 22.5 && f < 25.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#fff000"));
            } else if (f >= 25.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 15 && a < 20) {
            under.setText("< 18.6");
            normal.setText("18.6 - 23.9");
            over.setText("23.9 -26.7");
            obese.setText("> 26.7");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 18.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 18.6 && f < 23.9) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 23.9 && f < 26.7) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#fff000"));
            } else if (f >= 26.7) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 20) {
            under.setText("< 20");
            normal.setText("20 - 25");
            over.setText("25 - 30");
            obese.setText("30 - 40");
            morobese.setText("> 40");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 20) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 20 && f < 25) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 25 && f < 30) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#fff000"));
            } else if (f >= 30 && f < 40) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            } else {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff0400"));
            }
        }
    }

    private void showdatawomen(float f, int a) {
        TextView under = (TextView) findViewById(R.id.underweight);
        TextView normal = (TextView) findViewById(R.id.normal);
        TextView over = (TextView) findViewById(R.id.overweight);
        TextView obese = (TextView) findViewById(R.id.obese);
        TextView morobese = (TextView) findViewById(R.id.morobese);
        TextView tv = (TextView) findViewById(R.id.result);
        if (a < 10) {
            under.setText("< 14.2");
            normal.setText("14.2 - 20.6");
            over.setText("20.6 - 23.3");
            obese.setText("> 23.3");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 14.2) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 14.2 && f < 20.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 20.6 && f < 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#fff000"));
            } else if (f >= 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 10 && a < 15) {
            under.setText("< 15");
            normal.setText("15 - 21.4");
            over.setText("21.4 - 23.3");
            obese.setText("> 23.3");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 15) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 15 && f < 21.4) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 21.4 && f < 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#fff000"));
            } else if (f >= 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 15 && a < 20) {
            under.setText("< 17.8");
            normal.setText("17.8 - 23.3");
            over.setText("23.3 - 25.6");
            obese.setText("> 25.6");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 17.8) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 17.8 && f < 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 23.3 && f < 25.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#fff000"));
            } else if (f >= 25.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 20) {
            under.setText("< 19");
            normal.setText("19 - 24");
            over.setText("24 - 30");
            obese.setText("30 - 40");
            morobese.setText("> 40");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 19) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 19 && f < 24) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 24 && f < 30) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#fff000"));
            } else if (f >= 30 && f < 40) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            } else {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff0400"));
            }
        }
    }

    public void result(View view) {
        Button button = (Button) findViewById(R.id.res);
        age3 = ageit();
        height3 = heightit();
        weight3 = weightit();
        if ((gender == 0 || gender == 1) && t1 == 1 && t2 == 1 && t3 == 1) {
            temp1 = bmiCalculator(height3, weight3);
            t4 = 1;
            if (gender == 0)
                showdatamen(temp1, age3);
            else
                showdatawomen(temp1, age3);
        } else if (t1 == 0 || t2 == 0 || t3 == 0) {
            Toast.makeText(this, "Choose the Required Fields", Toast.LENGTH_SHORT).show();
            age3 = ageit();
            height3 = heightit();
            weight3 = weightit();
        } else {
            Toast.makeText(this, "Choose Gender", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(View view) {
        Animation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(600);
        Button button = (Button) findViewById(R.id.reset);
        button.startAnimation(animation);
        if (t4 != 0) {
            TextView under = (TextView) findViewById(R.id.underweight);
            TextView normal = (TextView) findViewById(R.id.normal);
            TextView over = (TextView) findViewById(R.id.overweight);
            TextView obese = (TextView) findViewById(R.id.obese);
            TextView morobese = (TextView) findViewById(R.id.morobese);
            age2 = 0; age3 = 0;
            height2 = 0;height3 = 0;
            weight2 = 0;weight3 = 0;
            gender = -1;
            t1 = 0;t4 = 0;t2 = 0;t3 = 0;
            temp1 = 0;temp2 = 0;temp3 = 0;
            age1 = "";height1 = "";weight1 = "";
            TextView t = (TextView) findViewById(R.id.result);
            t.setText("0.0");
            t.setBackgroundColor(Color.parseColor("#FFCDCDC6"));
            rg.clearCheck();
            under.setText("-");
            normal.setText("-");
            over.setText("-");
            obese.setText("-");
            morobese.setText("-");
            height.setText("");
            weight.setText("");
            age.setText("");
        }
    }


}
