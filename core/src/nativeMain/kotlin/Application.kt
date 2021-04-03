package konanui.core

abstract class Application protected constructor() : Disposable {
	//private val windows = mutableListOf<Window>()

	companion object {
		operator fun invoke(): Application = PlatformApplication()
	}

	override fun dispose() {
		//windows.forEach(Window::dispose)
	}

	abstract fun run()

	abstract fun finishQuitting()

	fun quit() {
		finishQuitting()
	}
}
