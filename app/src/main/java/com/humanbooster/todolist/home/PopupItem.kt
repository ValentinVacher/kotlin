package com.humanbooster.todolist.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.humanbooster.moduledatabase.entity.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupItem(viewModel: HomeViewModel, todo: Todo = Todo(0, ""), close: () -> Unit){
    var text by remember { mutableStateOf("") }
    var popupTitle by remember { mutableStateOf("Ajouter une tâche") }

    if (todo.id != 0.toLong()){
        text = todo.text
        popupTitle = "Modifier une tâche"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
                .height(225.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 20.dp, end = 20.dp),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val (field, button, title, icon) = createRefs()

                IconButton(onClick = { close() },
                    modifier = Modifier.constrainAs(icon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close popup",
                    )
                }

                Text(text = popupTitle,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(title) {
                            top.linkTo(parent.top)
                            bottom.linkTo(field.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 10.dp)

                )

                TextField(value = text,
                    onValueChange = { text = it
                                    todo.text = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(2.dp, MaterialTheme.colorScheme.outline)
                        )
                        .constrainAs(field) {
                            top.linkTo(title.bottom)
                            bottom.linkTo(button.top)
                        },
                    label = { Text(text = popupTitle) })

                Button(onClick = {
                    if (text.isEmpty()) {
                        return@Button
                    }
                    else if(todo.id != 0.toLong()){
                        viewModel.onUpdate(todo)
                    }
                    else{
                        viewModel.onInsertTodo(Todo(text = text))
                    }
                    text = ""
                    close()
                }, modifier = Modifier.constrainAs(button) {
                    top.linkTo(field.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }) {
                    Text(text = popupTitle)
                }
            }
        }
    }
}