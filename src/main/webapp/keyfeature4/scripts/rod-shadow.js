zk.afterMount(function(){
	var container = zk.$('$container');
	var $container = jq(container);
	container.rowHeight = $container.data('rowheight');
	container.visibleSize = $container.data('visiblesize');
	container.cachedSize = $container.data('cachedsize');
	container.loading = false;
	container.begin = 0;
	container.scrollTop = 0;
	$container.on('scroll', function(evt) {
		if (container.loading) {
			$container[0].scrollTop = container.scrollTop;
			return;
		}
		var itemIndex = evt.target.scrollTop / container.rowHeight;
		if (itemIndex > (container.begin + container.cachedSize - container.visibleSize) || itemIndex < container.begin) {
			container.loading = true;
			container.scrollTop = jq('.list-group')[0].scrollTop;
			container.begin = Math.floor(itemIndex / container.visibleSize) * container.visibleSize;
			container.end = container.begin + container.visibleSize * 3;
			zkbind.$('$container').command('scrolling', {begin: container.begin, end: container.end});
			zkbind.$('$container').after('scrolling', function() {
				container.loading = false;
			});
		}
	});
});
