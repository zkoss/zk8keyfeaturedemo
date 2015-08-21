function initRodList(container, totalSize, rowHeight) {
	var $container = jq(container);
	var $topPadding = $container.find('> .topPadding');
	var $bottomPadding = $container.find('> .bottomPadding');
	var binder = zkbind.$(container);
	
	$container.on('scroll', function(evt) {
		if (container.loadingPosition) {
			$container.scrollTop(container.loadingPosition);
			return;
		}
		if ($bottomPadding.position().top < $container.height()) {
			loadData(Math.floor($container.scrollTop() / rowHeight), 'down');
		} else if ($topPadding.height() > $container.scrollTop()) {
			loadData(Math.ceil(($container.scrollTop() + $container.height()) / rowHeight), 'up');
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

	function loadData(index, direction) {
		container.loadingPosition = $container.scrollTop();
		binder.command('loadData', {loadingIndex: index, direction: direction});
	}
}
