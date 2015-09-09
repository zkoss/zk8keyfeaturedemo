function initRodList(container, totalSize, rowHeight) {
	var $container = jq(container);
	var $topPadding = $container.find('> .topPadding');
	var $bottomPadding = $container.find('> .bottomPadding');
	var binder = zkbind.$(container);

	container.viewHeight = 600;
	container.rowHeight = rowHeight;
	container.loadData = function (index, direction) {
		container.loadingPosition = $container.scrollTop();
		binder.command('loadData', {loadingIndex: index, direction: direction});
	}

	$container.on('scroll', function(evt) {
		if (container.loadingPosition) {
			$container.scrollTop(container.loadingPosition);
			return;
		}
		if ($bottomPadding.position().top < $container.height()) {
			container.loadData(Math.floor($container.scrollTop() / rowHeight), 'down');
		} else if ($topPadding.height() > $container.scrollTop()) {
			container.loadData(Math.ceil(($container.scrollTop() + $container.height()) / rowHeight), 'up');
		}
	});

	binder.after('loadData', function(pos) {
		container.loadingPosition = null;
	});

	binder.after('updateBegin', function(begin) {
		var numLoadedRows = $container.children().length - 2; //subtract top/bottomPadding
		$topPadding.height(begin * rowHeight);
		$bottomPadding.height((totalSize - numLoadedRows - begin) * rowHeight);
	});
}

function initPreviewBar(previewBar, totalSize) {
	var container = previewBar.$f('restaurantList');
	var preview = previewBar.$f('preview');
	var $container = jq(container.$n());
	var $previewBar = jq(previewBar);

	var index = 0;
	for (var w = previewBar.firstChild; w; w = w.nextSibling) {
		if (w.cityNumber) {
			w.$n().innerHTML = index % 2 == 0 ? w.cityInit : '';
			var h = Math.round(container.viewHeight * w.cityNumber / totalSize);
			h = h == 0 ? 1 : h;
			var style = w.$n().style;
			style.height = h + 'px';
			style.lineHeight = h + 'px';
			index++;
		}
	}
	preview.hide = function() {
		preview.shallShow = false;
		preview.$n().style.display = 'none';
	};

	var prevY = 0;
	$previewBar.click(function(evt) {
		var tg = evt.target;
		var w = zk.Widget.$(tg.id);
		if (w.cityNumber) {
			var y = evt.offsetY;
			if (y < 0) { 
				return;
			}
			y += jq(tg).offset().top
			var index = Math.floor(y * totalSize / container.viewHeight);
			binder.command('scrollIntoView', {loadingIndex: index, direction: prevY < y ? 'down' : 'up'});
			binder.after('updateScroll', function (begin) {
				$container.scrollTop(begin * container.rowHeight);
			});
			prevY = y;
		}
		preview.hide();
	});

	$previewBar.mouseover(function(evt) {
		preview.shallShow = true;
	});

	var binder = zkbind.$(previewBar), prevY = -1;
	$previewBar.mousemove(function(evt) {
		var y = evt.offsetY;
		if (!preview.shallShow)
			return;
		if (y < 0)
			return;
		if (prevY == y)
			return;
		
		var tg = evt.target;
		var w = zk.Widget.$(tg.id);
		if (w.cityNumber) {
			prevY = y;
			y += jq(tg).offset().top
			if (y > container.viewHeight)
				y = container.viewHeight;
			var index = Math.floor(y * totalSize / container.viewHeight);
			binder.command('showPreview', {showIndex: index, position: y});
		}
	});

	$previewBar.mouseout(function(evt) {
		preview.hide();
	});

	binder.after('updatePreview', function(view) {
		if (view) {
			var style = preview.$n().style;
			style.display = 'block';
			style.top = view.position + 'px';
		}
	});
}
