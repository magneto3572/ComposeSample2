package com.example.dogprofilepage.uiScreen

import android.content.res.Configuration.UI_MODE_TYPE_TELEVISION
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.dogprofilepage.R


@Composable
fun ProfilePage() {

    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp, 0.dp)

    )
    {
        BoxWithConstraints(modifier = Modifier.padding(10.dp)) {
            val constraints = if (minWidth < 600.dp) {
                portraitConstraints(margin = 16.dp)
            } else {
                landscapeConstraint(margin = 16.dp)
            }
            ConstraintLayout(constraints) {
                Image(
                    painter = painterResource(id = R.drawable.profilepic),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            width = 4.dp,
                            color = Color.Red,
                            shape = CircleShape
                        )
                        .layoutId("image"),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Bishal Singh",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.layoutId("nametext")
                )

                Text(text = "Bengaluru", modifier = Modifier.layoutId("citytext"))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowstats")

                ) {
                    profileStats(count = "150", title = "Followers")
                    profileStats(count = "100", title = "Following")
                    profileStats(count = "189", title = "Posts")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.layoutId("rowbut")
                ) {
                    Button(onClick = { }, modifier = Modifier.padding(10.dp, 0.dp)
                    ) {
                        Text(text = "Follow User")
                    }

                    Button(onClick = { }, modifier = Modifier.padding(10.dp, 0.dp)) {
                        Text(text = "Direct Message")
                    }
                }
            }
        }
    }
}

@Composable
fun profileStats(count: String, title : String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count , fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Composable
@Preview(showSystemUi = true)
fun prev(){
    ProfilePage()
}

@Composable
@Preview(showSystemUi = true, device = Devices.PIXEL_C)
fun prevLand(){
    ProfilePage()
}

private fun portraitConstraints(margin :Dp):ConstraintSet{
    return ConstraintSet{
        val image = createRefFor("image")
        val nametext = createRefFor("nametext")
        val citytext = createRefFor("citytext")
        val rowstats = createRefFor("rowstats")
        val rowbut = createRefFor("rowbut")
        constrain(image){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nametext){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(citytext){
            top.linkTo(nametext.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowstats){
            top.linkTo(citytext.bottom)
        }

        constrain(rowbut){
            top.linkTo(rowstats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.wrapContent
        }
    }
}

private fun landscapeConstraint(margin : Dp) : ConstraintSet{
    return ConstraintSet {
        val image = createRefFor("image")
        val nametext = createRefFor("nametext")
        val citytext = createRefFor("citytext")
        val rowstats = createRefFor("rowstats")
        val rowbut = createRefFor("rowbut")

        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }

        constrain(nametext){
            top.linkTo(image.bottom, margin = 10.dp)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }

        constrain(citytext){
            top.linkTo(nametext.bottom, margin = 8.dp)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }

        constrain(rowstats){
            top.linkTo(image.top)
            start.linkTo(image.end)
            end.linkTo(parent.end)
        }

        constrain(rowbut){
            end.linkTo(parent.end, margin = 10.dp)
            bottom.linkTo(parent.bottom)
        }

    }
}

