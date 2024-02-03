/*
 * This project is inspired by code from the Google Developer Training's Lemonade project.
 * See the original project here: https://github.com/google-developer-training/basic-android-kotlin-compose-training-lemonade
 */

package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonTreeAndJuiceApp()

        }
    }
}

@Composable
fun LemonTreeAndJuiceApp() {

    // mutableState starts with the value 1
    var step by remember { mutableStateOf(1) }

    var counter by remember { mutableStateOf(0) }

    when (step) {
        1 -> {
            LemonTreeJuiceImageText(
                // The system searches for the attribute `name="first_step"` in the strings.xml file.
                gameStepInfo = R.string.first_step,
                // Here, the system performs a similar search within res/drawable
                // to find the resource ID corresponding to the file name "lemon_tree".
                imageId = R.drawable.lemon_tree,
                textDescriptionInfo = R.string.lemon_tree_description,
                onImageClick = {
                    // This action triggers the system to invoke other Composable functions,
                    // passing updated arguments based on the new state
                    step = 2
                    // Counter is assigned a random value between 2 and 4, which will be utilized in the second step
                    counter = (2..4).random()
                }
            )
        }
        2 -> {
            LemonTreeJuiceImageText(
                gameStepInfo = R.string.second_step,
                imageId = R.drawable.lemon_squeeze,
                textDescriptionInfo = R.string.lemon_description,
                // When the image is clicked, the counter will decrease until it reaches 0.
                onImageClick = {
                    counter--
                    if (counter == 0) {
                        step = 3
                    }
                }
            )
        }

        3 -> {
            LemonTreeJuiceImageText(
                gameStepInfo = R.string.third_step,
                imageId = R.drawable.lemon_drink,
                textDescriptionInfo = R.string.lemonade_glass_description,
                onImageClick = {
                    step = 4
                }
            )
        }
        4 -> {
            LemonTreeJuiceImageText(
                gameStepInfo = R.string.fourth_step,
                imageId = R.drawable.lemon_restart,
                textDescriptionInfo = R.string.empty_glass_description,
                onImageClick = {
                    step = 1
                }
            )
        }
    }
}

@Composable
fun LemonTreeJuiceImageText(
    gameStepInfo: Int,
    imageId: Int,
    textDescriptionInfo: Int,
    onImageClick: () -> Unit
) {
    HeaderInfo(message = "Lemon Game")
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Image(
                    painter = painterResource(imageId),
                    contentDescription = stringResource(textDescriptionInfo),
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .padding(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(45.dp))
            Text(
                text = stringResource(gameStepInfo),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun HeaderInfo(message: String, modifier: Modifier = Modifier){
    Box{
        Row(
            modifier = Modifier
                .background(Color(0xFF33A5FF))
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center

        ){
            Text(
                text = message,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Preview
@Composable
fun LemonTreePreview() {
    LemonadeTheme() {
        LemonTreeAndJuiceApp()
    }
}