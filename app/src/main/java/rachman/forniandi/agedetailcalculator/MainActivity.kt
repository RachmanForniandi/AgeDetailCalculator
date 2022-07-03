package rachman.forniandi.agedetailcalculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import rachman.forniandi.agedetailcalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener {
            selectBirthdayDate()
        }
    }

    private fun selectBirthdayDate() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day =myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"Years was $selectedYear,month was ${selectedMonth+1}",
                    Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                binding.tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val outputDate = sdf.parse(selectedDate)
                outputDate.let {
                    val selectedDateInMinutes = outputDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        binding.tvSelectedDateInMinutes.text = differenceInMinutes.toString()

                    }
                }
            },year,month,day)
            //.show()
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()



    }
}