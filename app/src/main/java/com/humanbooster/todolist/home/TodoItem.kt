package com.humanbooster.todolist.home

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.humanbooster.moduledatabase.entity.Todo
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItem(
    todo: Todo, viewModel: HomeViewModel, update: () -> Unit
) {
    val sdf = SimpleDateFormat("HH:mm:ss\n dd/MM/yyyy", Locale.FRANCE)
    var isChecked by remember { mutableStateOf(todo.isDone) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        onClick = { update() }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 80.dp)
                .padding(8.dp),
        ) {
            val (checkBox, deleteButton, date, text) = createRefs()

            Checkbox(checked = isChecked,
                onCheckedChange = { checked ->
                    isChecked = checked
                    todo.isDone = checked
                    viewModel.onChecked(todo)
                },
                modifier = Modifier.constrainAs(checkBox) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })



            Text(text = todo.text, modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 60.dp, end = 75.dp)

            )

            Text(
                text = sdf.format(todo.createdAt),
                fontSize = 12.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier.constrainAs(date) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
                lineHeight = 15.sp
            )

            IconButton(onClick = { viewModel.onDeleteTodo(todo) },
                modifier = Modifier.constrainAs(deleteButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(date.top)
                }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete todo ${todo.id}",
                )
            }
        }
    }
}