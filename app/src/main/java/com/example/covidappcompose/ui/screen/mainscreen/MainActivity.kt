package com.example.covidappcompose.ui.screen.mainscreen

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.covidappcompose.MyApp
import com.example.covidappcompose.R
import com.example.covidappcompose.model.RawDataResponse
import com.example.covidappcompose.net.DataRepository
import com.example.covidappcompose.ui.theme.CovidAppComposeTheme
import com.example.covidappcompose.ui.theme.PopupBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance

class MainActivity : ComponentActivity() {

    private val repository: DataRepository by MyApp.kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Header(context = applicationContext)
            Loader()
        }

        getDataAndUpdateUI()
    }

    private fun getDataAndUpdateUI() {
        CoroutineScope(Dispatchers.Main).launch {

            withContext(Dispatchers.IO) {
                try {
                    val result = repository.getData().execute()

                    if (result.isSuccessful) {
                        runOnUiThread {
                            setContent {
                                CovidAppComposeTheme {
                                    Surface(
                                        modifier = Modifier.fillMaxSize(),
                                        color = MaterialTheme.colors.background
                                    ) {
                                        Column(modifier = Modifier.fillMaxSize()) {
                                            Header(context = applicationContext)

                                            GlobalCaseField(
                                                context = applicationContext,
                                                confirmed = result.body()?.summaryStats?.global?.confirmed.toString(),
                                                death = result.body()?.summaryStats?.global?.deaths.toString()
                                            )

                                            result.body()?.rawData?.let {
                                                AllCountriesField(
                                                    context = applicationContext,
                                                    data = it
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}

@Composable
fun Loader() {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
    }
}

@Composable
fun AllCountriesField(context: Context, data: List<RawDataResponse>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = data, itemContent = { item: RawDataResponse ->
            CountryField(context = context, data = item)
        })
    }
}

@Composable
fun CountryField(context: Context, data: RawDataResponse) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
            .background(PopupBackground, CircleShape)
    ) {
        Text(
            text = data.Combined_Key ?: "",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        ConfirmedRow(context = context, confirmed = data.Confirmed ?: "")
        DeathRow(context = context, death = data.Deaths ?: "")
    }
}

@Composable
fun ConfirmedRow(context: Context, confirmed: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 4.dp)
    ) {
        Text(text = context.getString(R.string.confirmed))
        Text(
            text = confirmed,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun DeathRow(context: Context, death: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 4.dp)
    ) {
        Text(text = context.getString(R.string.death))
        Text(
            text = death,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun GlobalCaseField(context: Context, confirmed: String, death: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(PopupBackground, CircleShape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = context.getString(R.string.global),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp),
                textAlign = TextAlign.Center
            )

            ConfirmedRow(context = context, confirmed = confirmed)

            DeathRow(context = context, death = death)
        }
    }
}

@Composable
fun Header(context: Context) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(PopupBackground, CircleShape)
    ) {
        Text(
            text = context.getString(R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 4.dp),
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
    }
}