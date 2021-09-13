<div class="form-group">
    <label for="${property}">${label} ${required? '*' : '' }</label>
    <input type="date" name="${property}" value="${g.formatDate(date:value, format:'yyyy-MM-dd')}" required="${property}" class="form-control underlined"/>
</div>

