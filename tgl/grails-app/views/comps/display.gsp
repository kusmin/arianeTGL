<div class="form-group">
    <label for="${name}">${label}</label>
    <g:if test="${type == 'text'}">
        <input type="text" name="${name}" class="form-control underlined" value="${value}"/>
    </g:if>
    
</div>