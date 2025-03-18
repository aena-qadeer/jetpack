package com.example.jetpacknavigation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpacknavigation.ui.theme.JetpackNavigationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController) {

    val userName = remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = { Text("My App") }, colors = TopAppBarDefaults.topAppBarColors(Color.Red))
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {

            TextField(
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                label = { Text("Enter your name") },
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Cyan
                ),
                modifier = Modifier.size(300.dp, 60.dp),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                shape = RoundedCornerShape(5.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.size(60.dp))
            Button(
                onClick = {
                    if(userName.value.isEmpty()){
                        return@Button
                    }
                    try {
                        navController.navigate("SecondPage/${userName.value}"){
                            popUpTo("MainPage"){
                                //inclusive = true
                            }
                        }
                    } catch (e: IllegalArgumentException) {
                        Log.d("MainPage", "MainPage: $e")
                    }

                    //   navController.navigate("SecondPage/${userName.value}")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                modifier = Modifier.size(200.dp,60.dp),
                border = BorderStroke(width = 2.dp, color = colorResource(R.color.teal_200))
            ) {
                Text(text = "Send")
            }
        }
    })
}

@Composable
fun MyLazyColumn() {
    LazyColumn {
        items(20) { index -> // Generates 20 items dynamically
            Text(
                text = "Item #$index",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 18.sp
            )
        }

    /*    items(10){index->
            Text(
                text = "Item 2 $index",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 18.sp
            )

        }*/
    }


}

@Composable
fun MyLazyRow(){
    LazyRow {
        items(10){
            Text(
                text = "Row $it",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun MyLazyVerticalGrid(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(10){
           Card(
               modifier = Modifier.padding(8.dp),
               shape = RoundedCornerShape(10.dp),
               border = BorderStroke(1.dp, Color.Black),
               elevation = CardDefaults.cardElevation(4.dp)
           ){
               Text(
                   text = "Item $it",
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(16.dp),
                   )
           }
        }
    }
}

@Composable
fun MyLazyHorizontalGrid() {
    LazyHorizontalGrid(
        rows = GridCells.Adaptive(100.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(10) { index ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text(
                    text = "Item #$index",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackNavigationTheme {
        //MainPage()
        //MyLazyColumn()
       // MyLazyRow()
       // MyLazyVerticalGrid()
        MyLazyHorizontalGrid()
    }
}
