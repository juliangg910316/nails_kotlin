package com.juliangg.nails.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juliangg.nails.R
import com.juliangg.nails.ui.theme.PrimaryColor
import com.juliangg.nails.ui.theme.Shapes

@Composable
fun RegisterScreen() {

    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var checkBoxOneState by remember {
        mutableStateOf(true)
    }
    var checkBoxTwoState by remember {
        mutableStateOf(true)
    }
    var isPasswordOpen by remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.register_illustration),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Create your account",
            textAlign = TextAlign.Center,
            color = PrimaryColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )

        TextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = PrimaryColor,
                backgroundColor = Color.White,
                cursorColor = PrimaryColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_person_24),
                        contentDescription = "",
                        tint = PrimaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color.Gray)
                    )
                }
            },
            placeholder = {
                Text(text = "Username", color = Color.Gray)
            },
            textStyle = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.SemiBold
            )

        )
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = PrimaryColor,
                backgroundColor = Color.White,
                cursorColor = PrimaryColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_email_24),
                        contentDescription = "",
                        tint = PrimaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color.Gray)
                    )
                }
            },
            placeholder = {
                Text(text = "Email", color = Color.Gray)
            },
            textStyle = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.SemiBold
            )

        )
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = PrimaryColor,
                backgroundColor = Color.White,
                cursorColor = PrimaryColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_lock_24),
                        contentDescription = "",
                        tint = PrimaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color.Gray)
                    )
                }
            },
            placeholder = {
                Text(text = "Password", color = Color.Gray)
            },
            textStyle = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.SemiBold
            ),
            visualTransformation = if (!isPasswordOpen) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                    if (!isPasswordOpen) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_visibility_24),
                            contentDescription = "",
                            tint = PrimaryColor,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_visibility_off_24),
                            contentDescription = "",
                            tint = PrimaryColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

        )

        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkBoxOneState, onCheckedChange = { checkBoxOneState = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryColor,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.White
                ),
                modifier = Modifier.clip(shape = Shapes.medium)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Log In with Email", color = Color.White, fontSize = 12.sp)

        }

        Row(
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkBoxTwoState, onCheckedChange = { checkBoxTwoState = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryColor,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.White
                ),
                modifier = Modifier.clip(shape = Shapes.medium)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Email me about special pricing", color = Color.White, fontSize = 12.sp)

        }

        Button(
            onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                backgroundColor = PrimaryColor
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            )
        ) {
            Text(
                text = "Sign Up with Email",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        SocialMediaSignInButtons()

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Text(
                text = "Already have an account? Sign In",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun SocialMediaSignInButtons() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp).padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            modifier = Modifier.clip(shape = Shapes.large),
            contentPadding = PaddingValues(horizontal = 26.dp, vertical = 10.dp)
        ) {
            Row() {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_computer_24),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Google", color = PrimaryColor)
            }
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            modifier = Modifier.clip(shape = Shapes.large),
            contentPadding = PaddingValues(horizontal = 26.dp, vertical = 10.dp)
        ) {
            Row() {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_computer_24),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Facebook", color = PrimaryColor)
            }
        }
    }
}