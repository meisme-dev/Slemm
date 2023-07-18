package com.slemmapp.slemm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SlemmBar() {
    Surface(
        tonalElevation = 16.dp,
        shadowElevation = 16.dp,
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp)
                )
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "All",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 12.dp),
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.ExpandMore, "Search")
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Search, "Search")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.MoreVert, "Overflow Menu")
                    }
                }
            }
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SlemmChip(true, "Top")
                Padding(6.dp)
                SlemmChip(false, "Hot")
                Padding(6.dp)
                SlemmChip(false, "Active")
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.ExpandMore, "More")
                }
            }
        }
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun SlemmBar() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
//            .padding(vertical = 3.dp),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        IconButton(onClick = { /*TODO*/ }) {
//            Icon(Icons.Rounded.Menu, "Open drawer")
//        }
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable {  }.padding(6.dp)
//        ) {
//            Text("Home")
//            Icon(Icons.Rounded.ExpandMore, "Expand", modifier = Modifier.defaultMinSize(0.dp))
//        }
//        IconButton(onClick = { /*TODO*/ }) {
//            Icon(Icons.Rounded.Refresh, "Reload")
//        }
//    }
//}