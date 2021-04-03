package konanui.samples

import kotlinx.cinterop.*
import platform.gtk4.*

fun activate(app: CPointer<GtkApplication>?) {
	val window = gtk_application_window_new(app)
	gtk_window_set_title(window?.reinterpret(), "Window")
	gtk_window_set_default_size(window?.reinterpret(), 200, 200)
	gtk_widget_show(window)
}

fun main() {
	val application = gtk_application_new("org.gtk.example", G_APPLICATION_FLAGS_NONE)
	g_signal_connect_data(
		application, "activate",
		staticCFunction { app: CPointer<GtkApplication>?, _: COpaquePointer? ->
			activate(app)
		}.reinterpret(),
		null, null, 0u
	)
	val status = g_application_run(application?.reinterpret(), 0, null)
	g_object_unref(application)
	println(status)
}
