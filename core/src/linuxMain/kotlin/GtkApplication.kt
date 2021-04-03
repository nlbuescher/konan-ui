package konanui.core

import platform.gtk4.*

class GtkApplication : Application() {
	val app = gtk_application_new("", G_APPLICATION_FLAGS_NONE)

	override fun run() = TODO()
	override fun finishQuitting() = TODO()
}
