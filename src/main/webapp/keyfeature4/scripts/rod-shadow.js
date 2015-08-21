function initRodList(container) {
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
			loadData(Math.floor($container.scrollTop() / container.rowHeight), 'down');
		} else if ($topPadding.height() > $container.scrollTop()) {
			loadData(Math.ceil(($container.scrollTop() + $container.height()) / container.rowHeight), 'up');
		}
	});
	
	binder.after('loadData', function(pos) {
		container.loadingPosition = null;
	});
	
	binder.after('updateBegin', function(begin) {
		$topPadding.height(begin * container.rowHeight);
		$bottomPadding.height((container.totalSize - container.cacheSize - begin) * container.rowHeight);
		console.log(begin);
	});


	function loadData(index, direction) {
		container.loadingPosition = $container.scrollTop();
		binder.command('loadData', {loadingIndex: index, direction: direction});
	}
}
