package com.juliangg.nails.features.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juliangg.nails.R
import com.juliangg.nails.ui.theme.BottomBoxShape
import com.juliangg.nails.ui.theme.LightTextColor
import com.juliangg.nails.ui.theme.PrimaryColor

@Composable
fun LoginScreen() {
    BackgroundImage()
    LoginArea()
}

@Composable
fun LoginArea() {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isPasswordOpen by remember {
        mutableStateOf(false)
    }

    Box (
        contentAlignment = Alignment.BottomCenter
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "WELCOME TO BEIDIS NAILS",
                fontSize = 20.sp,
                color = PrimaryColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_computer_24),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Continue with Google",
                        color = PrimaryColor,
                        fontSize = 16.sp
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
                shape = BottomBoxShape.medium
            ) {
                Column(horizontalAlignment =  Alignment.CenterHorizontally) {
                    Text(text = "Log In with Email",
                        color = LightTextColor,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(text = "Email", color = PrimaryColor)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = PrimaryColor,
                            focusedBorderColor = PrimaryColor,
                            textColor = PrimaryColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        keyboardActions = KeyboardActions { Log.i("TAG", "LoginArea: ") },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_email_24),
                                contentDescription = "",
                                tint = PrimaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(text = "Password", color = PrimaryColor)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = PrimaryColor,
                            focusedBorderColor = PrimaryColor,
                            textColor = PrimaryColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        keyboardActions = KeyboardActions { Log.i("TAG", "LoginArea: ") },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_lock_24),
                                contentDescription = "",
                                tint = PrimaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                        },
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

                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 20.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = PrimaryColor,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(text = "Login")

                    }
                    
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Forgot Password?",
                        color = LightTextColor,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 26.dp)
                        )
                    }

                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Don't have an account? Sign Up",
                            color = LightTextColor,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun BackgroundImage() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.login_illustration),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
    }
}