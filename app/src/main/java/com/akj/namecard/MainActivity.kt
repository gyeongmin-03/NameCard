package com.akj.namecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.akj.namecard.ui.theme.NameCardTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NameCardTheme {
                val userData by remember { mutableStateOf(MyViewModel()) }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp))
                    {
                        MyCard(userData)
                        ChangeInformButtonList(userData)
                    }
                }
            }
        }
    }
}

@Composable
fun ChangeInformButtonList(userData: MyViewModel){

    val arr = listOf("사진", "이름", "나이", "생년월일", "이메일", "상세정보")
    val itemMod = Modifier.fillMaxWidth()

    var clickText by remember {
        mutableStateOf("")
    }

    LazyColumn(modifier = Modifier.padding(top = 20.dp)){

        items(arr) { value ->
            Button(
                modifier = itemMod,
                onClick = {
                    clickText = value
                }
            ) {
                Text(text = "$value 변경")
            }
        }
    }

    when (clickText) {
        in arr -> {
            when (arr.indexOf(clickText)) {
                0 -> PhotoDialog()
                1 -> NameDialog(userData) { clickText = "" }
                2 -> AgeDialog(userData) { clickText = "" }
                3 -> BirthDialog(userData) { clickText = "" }
                4 -> EmailDialog(userData) { clickText = "" }
                5 -> InformDialog(userData) { clickText = "" }
            }
        }
    }
}

@Composable
fun PhotoDialog(){

}


@Composable
fun NameDialog(userData: MyViewModel, onDismiss: () -> Unit){
    var name by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = {
            onDismiss() },
        confirmButton = { TextButton(onClick = {
            userData.setName(name)
            onDismiss()
        }) {
            Text(text = "확인")

        } },
        modifier = Modifier,
        dismissButton = { TextButton(onClick = {
            onDismiss()
        }) {
            Text(text = "취소")
        } },
        title = { Text("이름 변경") },
        text = { OutlinedTextField(value =  name, onValueChange = {name = it})},
        backgroundColor = Color.White
    )
}


@Composable
fun AgeDialog(userData: MyViewModel, onDismiss: () -> Unit){
    var age by remember {
        mutableStateOf("")

    }

    AlertDialog(
        onDismissRequest = {
            onDismiss() },
        confirmButton = { TextButton(onClick = {
            userData.setAge(if(age.toInt() >= 0) age.toInt() else 0 )
            onDismiss()
        }) {
            Text(text = "확인")

        } },
        modifier = Modifier,
        dismissButton = { TextButton(onClick = {
            onDismiss()
        }) {
            Text(text = "취소")
        } },
        title = { Text("나이 변경") },
        text = { OutlinedTextField(value = age, onValueChange = {age = it}) },
        backgroundColor = Color.White
    )

}

@Composable
fun BirthDialog(userData: MyViewModel, onDismiss: () -> Unit){
    var birth by remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = {
            onDismiss() },
        confirmButton = { TextButton(onClick = {
            userData.setBirth(birth)
            onDismiss()
        }) {
            Text(text = "확인")

        } },
        modifier = Modifier,
        dismissButton = { TextButton(onClick = {
            onDismiss()
        }) {
            Text(text = "취소")
        } },
        title = { Text("생년월일 변경") },
        text = { OutlinedTextField(value =  birth, onValueChange = {birth = it})},
        backgroundColor = Color.White
    )
}

@Composable
fun EmailDialog(userData: MyViewModel, onDismiss: () -> Unit){
    var email by remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = {
            onDismiss() },
        confirmButton = { TextButton(onClick = {
            userData.setEmail(email)
            onDismiss()
        }) {
            Text(text = "확인")

        } },
        modifier = Modifier,
        dismissButton = { TextButton(onClick = {
            onDismiss()
        }) {
            Text(text = "취소")
        } },
        title = { Text("이름 변경") },
        text = { OutlinedTextField(value =  email, onValueChange = {email = it})},
        backgroundColor = Color.White
    )
}

@Composable
fun InformDialog(userData: MyViewModel, onDismiss: () -> Unit){
    var inform by remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = {
            onDismiss() },
        confirmButton = { TextButton(onClick = {
            userData.setInform(inform)
            onDismiss()
        }) {
            Text(text = "확인")

        } },
        modifier = Modifier,
        dismissButton = { TextButton(onClick = {
            onDismiss()
        }) {
            Text(text = "취소")
        } },
        title = { Text("이름 변경") },
        text = { OutlinedTextField(value =  inform, onValueChange = {inform = it})},
        backgroundColor = Color.White
    )
}


@Composable
fun MyCard(userData: MyViewModel){
    val textValue by rememberSaveable {
        mutableStateOf("입력")
    }
    Surface(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray)
        ) {
            Row {
                Image(
                    Icons.Default.AccountBox,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(3f)
                        .align(Alignment.CenterVertically)
                        .aspectRatio(1f)
                )
                MyInform(Modifier.weight(5f), userData)

            }
            OutlinedTextField(
                value = textValue,
                onValueChange = {},
                enabled = false,
                label = { Text("상세")},
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
            )
        }
    }
}

@Composable
fun MyInform(modifier: Modifier = Modifier, userData: MyViewModel) {
    LazyColumn(modifier = modifier) {
        item{
            Text("이름 : ${userData.getName()}")
        }

        item{
            Text("나이 : ${userData.getAge()}")
        }

        item{
            Text("생년월일 : ${userData.getBirth()}")
        }

        item{
            Text("이메일 : ${userData.getEmail()}")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NameCardTheme {
        val userData by remember { mutableStateOf(MyViewModel()) }
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp))
            {
                MyCard(userData)
                ChangeInformButtonList(userData)
            }
        }

    }
}