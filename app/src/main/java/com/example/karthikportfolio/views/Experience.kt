package com.example.karthikportfolio.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.karthikportfolio.R

@Composable
fun Experience() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExperienceCard(
            title = "Senior Developer",
            company = "DBiz.AI",
            duration = "May 2025 – Present",
            role = "Frontend",
            icon = R.drawable.ic_work,
            details = listOf(
                "Enhanced Android expertise with Jetpack Compose and Kotlin.",
                "Completed 'Android 14 & Kotlin Masterclass' on Udemy.",
                "Focused on building scalable and modern UIs."
            )
        )

        ExperienceCard(
            title = "Web UI Developer",
            company = "Infosys",
            duration = "Jan 2023 – May 2025",
            role = "Systems Engineer & Senior System Associate",
            icon = R.drawable.ic_work,
            details = listOf(
                "1.5 years of experience in React.js and CSS using VS Code.",
                "Led frontend design and managed deployments.",
                "Collaborated with backend teams using Git.",
                "Used GitHub Copilot for writing clean, maintainable code."
            )
        )

        ExperienceCard(
            title = "Android Developer",
            company = "Infosys",
            duration = "Jan 2022 – Jan 2023",
            role = "System Associate",
            icon = R.drawable.ic_work,
            details = listOf(
                "2.5 years of experience developing Android apps in Android Studio.",
                "Published apps on Google Play Store showcasing hands-on experience.",
                "Integrated REST APIs and followed OOP best practices."
            )
        )
    }
}

@Composable
fun ExperienceCard(
    title: String,
    company: String,
    duration: String,
    role: String? = null,
    icon: Int,
    details: List<String>
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "Work Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_company),
                    contentDescription = "Company Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = company,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Calendar Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = duration,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            role?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            details.forEach { point ->
                Text(
                    text = "• $point",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
