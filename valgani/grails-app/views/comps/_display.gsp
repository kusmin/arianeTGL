<div class="igra_field row">
    <div class="igra_field_label col-md-4 col-sm-12">${label}</div>
    <g:if test="${raw(value) && (raw(value).toString().startsWith("https://") || raw(value).toString().startsWith("http://"))}">
        <div class="igra_field_value col-md-8 col-sm-12">
            <a href="${raw(value)}" target="_blank" rel="noopener noreferrer">${raw(value)}</a>
        </div>
    </g:if>
    <g:else>
        <div class="igra_field_value col-md-8 col-sm-12">${raw(value)}</div>
    </g:else>
</div>