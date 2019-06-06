const gulp = require("gulp");

// just for development
var browserSync = require('browser-sync').create();

function setUpWatch() {
	gulp.watch(['./js/**/*', './index.html', './views/**/*', './assets/**/*'], function() {
		browserSync.reload();
	});
}

gulp.task('browser-sync', function() {
	setUpWatch();
	browserSync.init({
		server: {
			baseDir: "./"
		}
	});
});
