function namespace(namespaceDeclaration) {
	var parts = namespaceDeclaration.split('.');
    var parent = window;
    var currentPart = '';    
    
	for(var i = 0, length = parts.length; i < length; i++) {
	    currentPart = parts[i];
	    parent[currentPart] = parent[currentPart] || {};
	    parent = parent[currentPart];
	}
	
	return parent;
}
