package com.litobumba.challenge7dayscode


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.litobumba.challenge7dayscode.ui.theme.Challenge7daysCodeTheme
import com.litobumba.challenge7dayscode.webclient.Dto
import com.litobumba.challenge7dayscode.webclient.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge7daysCodeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val call = Retrofit().api().pegarUsuario("lito-bumba")
                    call.enqueue(object : Callback<Dto?> {
                        override fun onResponse(call: Call<Dto?>, response: Response<Dto?>) {
                            response?.body()?.let { usuario ->
                                Log.i("Rest API", "${usuario.name}\n${usuario.bio}")
                            }
                        }

                        override fun onFailure(call: Call<Dto?>, t: Throwable) {
                            Log.e("Erro na Requisição", "${t?.message}")
                        }

                    })

                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box {
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .background(Color.DarkGray)
                    .height(150.dp)
            )
            SubcomposeAsyncImage(
                model = "https://avatars.githubusercontent.com/u/90806272?v=4",
                contentDescription = "profile-picture",
                modifier = Modifier
                    .align(BottomCenter)
                    .height(150.dp)
                    .width(150.dp)
                    .offset(x = 0.dp, y = 75.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            ){
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error)
                    CircularProgressIndicator(
                        color = Color.Gray, strokeWidth = 20.dp,
                        modifier = Modifier.background(Color.White))
                else SubcomposeAsyncImageContent()
            }
        }

        Spacer(modifier = Modifier.size(75.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Lito Bumba",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(CenterHorizontally)
            )
            Text(
                text = "lito-bumba",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Android Developer | Kotlin Language",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Repository",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(10.dp))

            Repository(
                "estudos",
                "Repositorio apenas para Estudos realizado"
            )

            Repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            Repository(
                "aboutme",
                "Site sobre mim feito para imersão Hipers CSS"
            )

            Repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            Repository(
                "aboutme",
                "Site sobre mim feito para imersão Hipers CSS"
            )

            Repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            Repository(
                "repositorio 4",
                "Repositorio apenas para teste 4"
            )

        }
    }
}

@Composable
fun Repository(title: String, description: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp))
            .background(Color.DarkGray)
            .padding(9.dp, 4.dp),
        content = {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    )
    Text(
        text = description,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Preview(showBackground = true)
@Composable
fun ShowProfileScreen() {
    ProfileScreen()
}