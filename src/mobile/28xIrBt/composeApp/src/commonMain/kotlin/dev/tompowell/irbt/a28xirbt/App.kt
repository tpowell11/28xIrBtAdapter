package dev.tompowell.irbt.a28xirbt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.
import androidx.compose.ui.unit.dp
import com.juul.kable.Bluetooth.BaseUuid
import org.jetbrains.compose.ui.tooling.preview.Preview

val GATT_SERVICE_UUID = BaseUuid + 0x1801
val GATT_CHAR_DEFAULT_SETUP = BaseUuid + 0x1
val GATT_CHAR_IDENTIFICATION = BaseUuid + 0x2
val GATT_CHAR_RESET_INSTRUMENT = BaseUuid + 0x4
val GATT_CHAR_RESET_METER_PROP = BaseUuid + 0x8
val GATT_CHAR_QUERY_PRIMARY_MEASUREMENT = BaseUuid + 0x10
val GATT_CHAR_QUERY_DISPLAYED_DATA = BaseUuid + 0x20

data class IrBtInfo (
    val serial_number: String
)

enum class ApplicationPages (
    val label: String,
    val icon: Int,
    val desc: String
){
    DEVICES("Devices", 0, "Available Devices"),
    VIEWDEVICE("View", 0, "View Selected Device"),
}
@Composable
fun NavigationHost(
    controller: NavHost
)
@Composable
@Preview
fun App() {
    MaterialTheme {
//        var showContent = remember { mutableStateOf(false) }
//        Column(
//            modifier = Modifier
//                .background(MaterialTheme.colorScheme.primaryContainer)
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Button(onClick = { showContent.value = !showContent.value }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent.value) {
////                val greeting = remember { Greeting().greet() }
////                Column(
////                    modifier = Modifier.fillMaxWidth(),
////                    horizontalAlignment = Alignment.CenterHorizontally,
////                ) {
////                    Image(painterResource(Res.drawable.compose_multiplatform), null)
////                    Text("Compose: $greeting")
////                }
//                Card(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .safeContentPadding()
//                ) {
//                    Button(onClick = { showContent.value = !showContent.value }) {
//                        Text("Back")
//                    }
//                }
//            }
//        }


//        var chosen_device = remember { mutableStateOf("") }
//
//        Column (
//            modifier = Modifier
//                .background(color = MaterialTheme.colorScheme.primaryContainer)
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            DeviceCard(IrBtInfo("A28XIRBT"), chosen_device)
//            DeviceCard(IrBtInfo("A28XIRBTa"), chosen_device)
//        }
        
        Scaffold (
            topBar = {
                NavigationBar {
                    Destinat
                }
            }
        ) {}

    }
}

@Composable
fun DeviceCard(info: IrBtInfo, cd: MutableState<String>) {
    Button(onClick = {
        cd.value = (info.serial_number)
        println("Set cd to ${info.serial_number}")
    },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(info.serial_number,Modifier.padding(vertical = 4.dp))
    }
}

@Composable
fun DeviceDetail(cd: MutableState<String>) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
    ) {
        Button(onClick = {
            cd.value = ("")
        }) {
            Text("Back")
        }
    }
}