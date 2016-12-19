var TIME = 30; // in seconds
var countTimer = TIME;
var processTimer;
var timer_is_on = 0;
var redirectPage = "#{request.contextPath}/showClassSelection.xhtml";

var countDownDiv = "dialog-countdown";
var txtCountDown = null;
if (!txtCountDown)
	txtCountDown = document.getElementById(countDownDiv);

function startIdleMonitor() {
	countTimer = TIME;
	txtCountDown.innerHTML = countTimer;
	PF('timeoutDialog').show();
}

function timedCount() {
	txtCountDown.innerHTML = countTimer;
	if (countTimer == 0) {
		stopCount();
		return;
	}
	countTimer = countTimer - 1;
	processTimer = setTimeout("timedCount()", 1000);
}

function doTimer() {
	if (!timer_is_on) {
		timer_is_on = 1;
		timedCount();
	}
}

function stopCount() {
	clearTimeout(processTimer);
	timer_is_on = 0;
	keepAlive();
}
      