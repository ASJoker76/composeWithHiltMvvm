package test.co.kosong.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import test.co.kosong.R
import test.co.kosong.model.MenuItem
import test.co.kosong.model.User
import test.co.kosong.routes.Routes
import test.co.kosong.viewmodel.HomeViewModel

@Composable
fun HomeKasirScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val menuItems = listOf(
        MenuItem("Transaksi Langsung", R.drawable.ic_transaksi_langsung),
        MenuItem("Transaksi Masuk", R.drawable.ic_transaksi_masuk),
        // Tambahkan item menu lainnya sesuai kebutuhan
    )

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(menuItems) { menuItem ->
            MenuItemCardKasir(menuItem = menuItem, onItemClick = {
                // Aksi yang akan diambil saat item menu diklik
                // Di sini Anda dapat menambahkan logika untuk menangani klik pada item menu
            })
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.Login.name)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Logout")
                }
            }
        }
    }
}

@Composable
fun MenuItemCardKasir(menuItem: MenuItem, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = menuItem.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = menuItem.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = onItemClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Order")
            }
        }
    }
}