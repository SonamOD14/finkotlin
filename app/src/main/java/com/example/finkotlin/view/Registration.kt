package com.example.finkotlin.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finkotlin.R
import androidx.compose.ui.unit.sp
import com.example.finkotlin.model.UserModel
import com.example.finkotlin.viewmodel.UserViewModel
import com.example.finkotlin.repository.UserRepoImpl
import com.example.finkotlin.ui.theme.Blue
import com.example.finkotlin.ui.theme.Green
import com.example.finkotlin.ui.theme.Lightgray
import com.example.finkotlin.ui.theme.Mint
import com.example.finkotlin.view.ui.theme.FutsalTheme
import com.example.finkotlin.view.ui.theme.Yellowish


class Registration : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FutsalTheme {
                RegistrationBody()
            }
        }
    }
}

@Composable
fun RegistrationBody(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }
    var terms by remember { mutableStateOf(false) }


    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val userViewModel = remember { UserViewModel(UserRepoImpl()) }

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }

    val datepicker = DatePickerDialog(
        context, { _, y, m, d ->
            selectedDate = "$y/${m + 1}/$d"

        }, year, month, day
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(vertical = 20.dp),
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Welcome Back",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Sign in to continue to Goalpost",
                    color = Color.White,
                    fontSize = 16.sp
                )

            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Lightgray,
                        focusedContainerColor = Lightgray,
                        focusedIndicatorColor = Green,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(15.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = firstname,
                    onValueChange = { firstname = it },
                    placeholder = { Text("First Name") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Lightgray,
                        focusedContainerColor = Lightgray,
                        focusedIndicatorColor = Green,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(15.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = lastname,
                    onValueChange = { lastname = it },
                    placeholder = { Text("Last Name") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Lightgray,
                        focusedContainerColor = Lightgray,
                        focusedIndicatorColor = Green,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(15.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = gender,
                    onValueChange = { gender = it },
                    placeholder = { Text("Gender") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Lightgray,
                        focusedContainerColor = Lightgray,
                        focusedIndicatorColor = Green,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(15.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .clickable { datepicker.show() }
                ) {
                    OutlinedTextField(
                        value = selectedDate,
                        onValueChange = {},
                        enabled = false,
                        placeholder = { Text("dd/mm/yyyy") },
                        colors = TextFieldDefaults.colors(
                            disabledContainerColor = Lightgray,
                            disabledIndicatorColor = Color.Transparent,
                            disabledTextColor = Color.Black,
                            disabledPlaceholderColor = Color.Gray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("**********") },
                    trailingIcon = {
                        IconButton(onClick = { visibility = !visibility }) {
                            Icon(
                                painter = if (!visibility)
                                    painterResource(R.drawable.baseline_visibility_off_24)
                                else
                                    painterResource(R.drawable.baseline_visibility_24),
                                contentDescription = null,
                            )
                        }
                    },
                    visualTransformation = if (!visibility) PasswordVisualTransformation() else VisualTransformation.None,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Lightgray,
                        focusedContainerColor = Lightgray,
                        focusedIndicatorColor = Green,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(15.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = terms,
                        onCheckedChange = { terms = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            checkmarkColor = Color.White
                        )
                    )
                    Text("I agree to terms & Conditions")
                }

                Button(
                    onClick = {
                        if (!terms) {
                            Toast.makeText(context, "Please agree to terms & conditions", Toast.LENGTH_LONG).show()
                        } else if (email.isBlank() || password.isBlank() || firstname.isBlank() || lastname.isBlank()) {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show()
                        } else {
                            userViewModel.register(email, password) { success, message, userId ->
                                if (success && userId != null) {
                                    val model = UserModel(
                                        userId = userId,
                                        firstName = firstname,
                                        lastName = lastname,
                                        email = email,
                                        dob = selectedDate,
                                        gender = gender
                                    )
                                    userViewModel.addUserToDatabase(userId, model) { dbSuccess, dbMessage ->
                                        if (dbSuccess) {
                                            Toast.makeText(context, dbMessage, Toast.LENGTH_LONG).show()
                                            val intent = Intent(context, Login::class.java)
                                            context.startActivity(intent)
                                            (context as? Activity)?.finish()
                                        } else {
                                            Toast.makeText(context, dbMessage, Toast.LENGTH_LONG).show()
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, message ?: "Registration failed", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Sign Up", fontSize = 20.sp)
                }

                Text(
                    buildAnnotatedString {
                        append("Already have an account? ")
                        withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                            append("Sign In")
                        }
                    },
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(context, Login::class.java)
                            context.startActivity(intent)
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun RegistrationBodyPreview(){
    FutsalTheme {
        RegistrationBody()
    }
}
