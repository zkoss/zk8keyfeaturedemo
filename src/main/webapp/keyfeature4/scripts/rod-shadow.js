zk.afterMount(function() {
	/* Data List */
	var container = zk.Widget.$('$container');
	var $container = jq(container);
	var $topPadding = $container.find('> .topPadding');
	var $bottomPadding = $container.find('> .bottomPadding');
	var numLoadedRows = $container.children().length - 2; //subtract top/bottomPadding
	var binder = zkbind.$('$container');
	// initialize necessary information
	container.viewHeight = $container.height();
	container.rowHeight = $container.find('> .list-group-item').outerHeight();
	container.loadData = function (index, direction) {
		container.loadingPosition = $container.scrollTop();
		binder.command('loadData', {loadingIndex: index, direction: direction});
	}
	binder.after('initData', function(totalSize) {
		container.totalSize = totalSize;
		$bottomPadding.height((container.totalSize - numLoadedRows) * container.rowHeight);
	});
	// register scroll event
	$container.on('scroll', function(evt) {
		if (container.loadingPosition) {
			$container.scrollTop(container.loadingPosition);
			return;
		}
		if ($bottomPadding.position().top < $container.height()) {
			container.loadData(Math.floor($container.scrollTop() / container.rowHeight), 'down');
			showLoading('bottom');
		} else if ($topPadding.height() > $container.scrollTop()) {
			container.loadData(Math.ceil(($container.scrollTop() + $container.height()) / container.rowHeight), 'up');
			showLoading('top');
		}
	});
	// callback after scrolling
	binder.after('loadData', function(pos) {
		container.loadingPosition = null;
		hideLoading();
	});
	// callback after scrolling
	binder.after('updateBegin', function(begin) {
		if (!container.totalSize)
			return;
		$topPadding.height(begin * container.rowHeight);
		$bottomPadding.height((container.totalSize - numLoadedRows - begin) * container.rowHeight);
	});
	function showLoading(direction) {
		var $spinner = jq('.spinner');
		$spinner.addClass(direction).css('display', 'block');
	}
	function hideLoading() {
		var $spinner = jq('.spinner');
		$spinner.removeClass('bottom top').css('display', 'none');
	}
	
	/* Preview */
	var previewBar = zk.Widget.$('$previewBar');
	var $previewLayer = jq(previewBar.$f('previewLayer'));
	var preview = previewBar.$f('preview');
	binder.after('initPreview', function(totalCitySize) {
		var index = 0;
		previewBar.totalCitySize = totalCitySize;
		for (var w = previewBar.firstChild; w; w = w.nextSibling) {
			if (w.cityNumber) {
				w.$n().innerHTML = index % 2 == 0 ? w.cityInit : '';
				var h = Math.round(container.viewHeight * w.cityNumber / totalCitySize);
				h = h == 0 ? 1 : h;
				var style = w.$n().style;
				style.height = h + 'px';
				style.lineHeight = h + 'px';
				index++;
			}
		}
	});
	// register mouse/click event on preview layer
	$previewLayer.mouseover(function(evt) {
		preview.shallShow = true;
	});
	var prevIndex = -1;
	$previewLayer.mousemove(function(evt) {
		var y = evt.offsetY;
		if (!preview.shallShow || y < 0) {
			return;
		}
		if (y > container.viewHeight)
			y = container.viewHeight;
		var index = Math.floor(y * previewBar.totalCitySize / container.viewHeight);
		if (prevIndex == index)
			return;
		prevIndex = index;
		preview.prevY = y;
		binder.command('showPreview', {showIndex: index});
	});
	$previewLayer.mouseout(function(evt) {
		preview.shallShow = false;
		preview.$n().style.display = 'none';
	});
	$previewLayer.click(function(evt) {
		var y = evt.offsetY;
		if (y < 0) { 
			return;
		}
		var index = Math.floor(y * previewBar.totalCitySize / container.viewHeight);
		if (y > container.viewHeight)
			y = container.viewHeight;
		binder.command('scrollIntoView', {loadingIndex: index});
	});
	// callback after click on preview layer
	binder.after('updatePreview', function(view) {
		if (view && preview.shallShow) {
			var style = preview.$n().style;
			style.display = 'block';
			style.top = preview.prevY + 'px';
		}
	});
	// callback after click on preview layer
	binder.after('updateScroll', function(begin) {
		$container.scrollTop(begin * container.rowHeight);
	});
});
